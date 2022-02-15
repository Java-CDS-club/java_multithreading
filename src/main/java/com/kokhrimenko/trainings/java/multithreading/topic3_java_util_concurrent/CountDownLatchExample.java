package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.CountDownLatch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link java.util.concurrent.CountDownLatch} example.
 *
 * @author kokhrime
 *
 */
public class CountDownLatchExample {
	private static final int THREADS_TO_WAIT = 3;

	public static void main(String[] args) throws InterruptedException {
		final SharedResource sharedResource = new SharedResource();
		Thread thread1 = new MyThread(sharedResource);
		Thread thread2 = new MyThread(sharedResource);
		Thread thread3 = new MyThread(sharedResource);

		thread1.start();
		thread2.start();
		Thread.sleep(1_000);
		thread3.start();
	}
	
	@AllArgsConstructor
	@Slf4j
	private static class MyThread extends Thread {
		private final SharedResource sharedResource;

		@Override
		public void run() {
			log.info("Thread: {} started in works");
			sharedResource.doSomething(getName());
		}
	}

	@Slf4j
	private static class SharedResource {
	    private final CountDownLatch countDownLatch = new CountDownLatch(THREADS_TO_WAIT);

		public void doSomething(final String threadName) {
			log.info("Thread: {} waiting till: {} threads will reach this section");
			countDownLatch.countDown();
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				log.error("Thread: {}, something went wrong: {}", threadName, e);
			}
			log.info("Thread: {} continue it work after waiting");
		}
	}
	
}
