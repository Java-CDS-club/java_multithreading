package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * This class demonstrates {@link Thread.join()} method.
 *
 * @author kokhrime
 *
 */
public class JoinExample implements Runnable {

	public void run() {
		Thread t = Thread.currentThread();

		System.out.println("Current thread: " + t.getName());
		System.out.println("Is Alive? " + t.isAlive());
	}

	public static void main(String args[]) throws Exception {
		Thread t = new Thread(new JoinExample());
		t.start();

		// Waits for 1000ms this thread to die.
		t.join(1000);

		System.out.println("Joining after 1000" + " milliseconds");
		System.out.println("Current thread: " + t.getName());

		// Checks if this thread is alive
		System.out.println("Is alive? " + t.isAlive());
	}
}
