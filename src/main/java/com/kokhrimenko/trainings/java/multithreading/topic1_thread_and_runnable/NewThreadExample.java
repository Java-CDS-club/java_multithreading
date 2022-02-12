package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * How to start a new thread by extending the {@link Thread} class.
 *
 * @author kokhrime
 *
 */
public class NewThreadExample extends Thread {
	public void run() {
		System.out.println("This is the way how to start a new thread by extending the Thread class.");
	}

	public static void main(String args[]) {
		new NewThreadExample().start();
	}
}
