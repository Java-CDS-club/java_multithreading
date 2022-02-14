package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ReentrantReadWriteLock} example.
 *
 * @author kokhrime
 *
 */
public class ReentrantReadWriteLockExample {
	private static final long WAIT_TIMEOUT = 1_000;

	public static void main(String[] args) throws InterruptedException {
		//read in paralled
		final SharedResource sharedResource = new SharedResource();
		Thread readThread1_1 = new ReadThread(sharedResource);
		Thread readThread1_2 = new ReadThread(sharedResource);
		readThread1_1.start();
		readThread1_2.start();
		readThread1_1.join();
		readThread1_2.join();

		//write thread will block read!
		Thread writeThread2_1 = new WriteThread(sharedResource);
		Thread readThread2_1 = new ReadThread(sharedResource);
		writeThread2_1.start();
		Thread.sleep(50);
		readThread2_1.start();
		writeThread2_1.join();
		readThread2_1.join();
	}
	
	@AllArgsConstructor
	private static class WriteThread extends Thread {
		private final SharedResource sharedResource;

		public void run() {
			sharedResource.doSomeWrite(getName());
		};
	}
	
	@AllArgsConstructor
	private static class ReadThread extends Thread {
		private final SharedResource sharedResource;

		public void run() {
			sharedResource.doSomeRead(getName());
		};
	}
	
	@Slf4j
	private static class SharedResource {
	    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	    public void doSomeRead(final String threadName) {
	        Lock readLock = readWriteLock.readLock();
			try {
				readLock.lock();
				log.info("Thread: {} entered shared read section", threadName);
				Thread.sleep(WAIT_TIMEOUT);
			} catch (InterruptedException e) {
				log.error("something went wrong. Error: {}", e);
			} finally {
				readLock.unlock();
			}
			log.info("Thread: {} left shared read section", threadName);
	    }

	    public void doSomeWrite(final String threadName) {
	        Lock writeLock = readWriteLock.writeLock();
			try {
				writeLock.lock();
				log.info("Thread: {} entered shared write section", threadName);
				Thread.sleep(WAIT_TIMEOUT);
			} catch (InterruptedException e) {
				log.error("something went wrong. Error: {}", e);
			} finally {
				writeLock.unlock();
			}
			log.info("Thread: {} left shared write section", threadName);
	    }
	}
}
