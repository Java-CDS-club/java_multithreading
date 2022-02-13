package com.kokhrimenko.trainings.java.multithreading.topic2_synchronized_keyword;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * How to use synchronized static method.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class SynchronizedStaticMethodExample {

	public static void main(String[] args) throws InterruptedException {
		MyThread thread1 = new MyThread();
		MyThread thread2 = new MyThread();
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		log.info("Thread1.getSharedResource() == Thread2.getShareREsource() = {}", thread1.getSharedResource() == thread2.getSharedResource());
	}

	private static class MyThread extends Thread {
		@Getter
		private MySharedResource sharedResource;
		
		@Override
		public void run() {
			sharedResource = MySharedResource.getInstance(getName());
			//do some other works here
		};
	}
	
	private static class MySharedResource {
		private static MySharedResource instance;

		private MySharedResource() {
			super();
		}

		public synchronized static MySharedResource getInstance(String threadName) {
			log.info("Thread: {} entering getInstance method", threadName);
			if(instance == null) {
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					log.error("Something wrong happened. Error: {}", e);
				}
				instance = new MySharedResource();
			}
			log.info("Thread: {} leaving getInstance method", threadName);
			return instance;
		}
	}

}
