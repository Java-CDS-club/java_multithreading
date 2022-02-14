package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ReentrantLock} example.
 *
 * @author kokhrime
 *
 */
public class ReentrantLockExample {

	public static void main(String[] args) {
		final SharedResource sharedResource = new SharedResource();
		Thread thread1 = new MyThread(sharedResource);
		Thread thread2 = new MyThread(sharedResource);

		thread1.start();
		thread2.start();
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
	    private final Lock lock = new ReentrantLock();

	    public void doSomething(final String threadName) {
	        lock.lock();
	        log.info("Thread: {} entered shared section", threadName);
			try {
				Thread.sleep(2_000);
			} catch (InterruptedException e) {
				log.error("something went wrong. Error: {}", e);
			} finally {
				lock.unlock();
			}
			log.info("Thread: {} left shared section", threadName);
	    }
	}
}
