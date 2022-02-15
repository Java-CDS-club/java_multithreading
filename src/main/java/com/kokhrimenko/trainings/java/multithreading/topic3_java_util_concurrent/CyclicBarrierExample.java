package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link CyclicBarrier} example.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class CyclicBarrierExample {
	private static final int THREADS_TO_WAIT = 3;

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS_TO_WAIT, () -> {
	        log.info("All previous tasks are completed");
	    });
		Thread t1 = new MyThread(cyclicBarrier); 
	    Thread t2 = new MyThread(cyclicBarrier); 
	    Thread t3 = new MyThread(cyclicBarrier); 

	    if (!cyclicBarrier.isBroken()) { 
	        t1.start(); 
	        t2.start(); 
	        t3.start(); 
	    }
	    t1.join();
	    t2.join();
	    t3.join();

	    Thread t4 = new MyThread(cyclicBarrier); 
	    Thread t5 = new MyThread(cyclicBarrier); 
	    Thread t6 = new MyThread(cyclicBarrier); 

	    if (!cyclicBarrier.isBroken()) { 
	        t4.start(); 
	        t5.start(); 
	        t6.start(); 
	    }
	}
	
	@AllArgsConstructor
	@Slf4j
	private static class MyThread extends Thread {
		private CyclicBarrier barrier;

		@Override
		public void run() {
			try {
	            log.info("{} is waiting", getName());
	            barrier.await();
	            log.info("{} is released", getName());
	        } catch (InterruptedException | BrokenBarrierException e) {
	            log.error("{} something went wrong. Error: {}", getName(), e);
	        }
		}
	}

}
