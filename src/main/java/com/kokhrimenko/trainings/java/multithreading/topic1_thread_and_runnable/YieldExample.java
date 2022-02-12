package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * This class demonstrates {@link Thread.yield()} method.
 *
 * @author kokhrime
 *
 */
public class YieldExample implements Runnable {

	public static void main(String[] args) {
		Runnable r = new YieldExample();
		new Thread(r).start();
		new Thread(r).start();
	}

	@Override
	public void run() {
		int counter = 0;
		while (counter < 5) {
			System.out.println(Thread.currentThread().getName());
			counter++;
			Thread.yield();
		}
	}

}
