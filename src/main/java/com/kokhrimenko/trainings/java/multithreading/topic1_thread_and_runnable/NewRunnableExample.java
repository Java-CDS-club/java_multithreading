package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * How to start a new thread by implementing {@link Runnable} interface.
 * 
 * @author kokhrime
 *
 */
public class NewRunnableExample implements Runnable {
	public void run() {
		System.out.println("This is the way how to start a new thread by implementing the Runnable interface.");
	}

	public static void main(String args[]) {
		new Thread(new NewRunnableExample()).start();
	}
}
