package com.kokhrimenko.trainings.java.multithreading.topic2_synchronized_keyword;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * How to use synchronized block.
 * 
 * @author kokhrime
 *
 */
@Slf4j
public class SynchronizedBlockExample {

	public static void main(String[] args) {
		MySharedResource criticalResource = new MySharedResource();

		Thread thread1 = new SynchronizedBlockThread(criticalResource);
		Thread thread2 = new SynchronizedBlockThread(criticalResource);

		thread1.start();
		thread2.start();
	}

	private static class MySharedResource {
		private Object criticalResource = new Object();

		public void doSomethingOnCriticalResource(String threadName) {
			log.info("Thread: {}, before entering synchronized section", threadName);

			synchronized (criticalResource) {
				log.info("Thread: {}, inside the critical section: start", threadName);
				try {
					Thread.sleep(2_000);
				} catch (InterruptedException e) {
					log.error("Something went wrong. Error: {}", e);
				}
				log.info("Thread: {}, inside the critical section: end", threadName);
			}

			log.info("Thread: {}, after synchronized section", threadName);
		}
	}

	@AllArgsConstructor
	private static class SynchronizedBlockThread extends Thread  {
		private final MySharedResource sharedResource;

		@Override
		public void run() {
			sharedResource.doSomethingOnCriticalResource(this.getName());
		}
	}
}
