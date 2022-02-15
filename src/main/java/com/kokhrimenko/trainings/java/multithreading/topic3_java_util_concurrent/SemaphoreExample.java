package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link Semaphore} example.
 *
 * @author kokhrime
 *
 */
public class SemaphoreExample {
	private static final int ALLOWED_THREADS = 5;

	public static void main(String[] args) {
		final SharedResource sharedResource = new SharedResource();

		IntStream.range(0, ALLOWED_THREADS + 3).forEach(id -> new MyThread(sharedResource).start());
	}
	
	@AllArgsConstructor
	private static class MyThread extends Thread {
		private final SharedResource sharedResource;

		@Override
		public void run() {
			sharedResource.doSomething(getName());
		}
	}

	@Slf4j
	private static class SharedResource {
	    private final Semaphore semaphore = new Semaphore(ALLOWED_THREADS);

		public void doSomething(final String threadName) {
			if (!semaphore.tryAcquire()) {
				log.info("Thread: {} cannot enter this critical section because we do not have any available permits at the moment",
						threadName);
				return;
			}
			log.info("Thread: {} entered critical section. Remain allowed permits: {}", threadName,
					semaphore.availablePermits());
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				log.error("something went wrong. Error: {}", e);
			} finally {
				semaphore.release();
			}
			log.info("Thread: {} left shared section", threadName);
		}
	}

}
