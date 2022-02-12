package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * What is going to be happen, if we call {@link Thread.run()} method.
 *
 * @author kokhrime
 *
 */
public class StartAThreadByRunMethod {

	public static void main(String[] args) {
		System.out.println("Before calling Thread.run() method");
		
		new Thread(new NewThread()).run();

		System.out.println("After calling Thread.run() method");
	}
	
	private static class NewThread implements Runnable {

		@Override
		public void run() {
			System.out.println("Start of a new method logic inside a thread");
			try {
				Thread.sleep(2_000);
			} catch (InterruptedException e) {
				System.err.println("Something wrong happened. Error: " + e);
			}
			System.out.println("End of a new method logic inside a thread");
		}

	}
}
