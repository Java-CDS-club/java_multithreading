package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

/**
 * Example when {@link Thread} in {@link Thread.State.BLOCKED}.
 *
 * @author kokhrime
 *
 */
public class ThreadInBlockedStateExample {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new ThreadInBlockedState());
		Thread t2 = new Thread(new ThreadInBlockedState());

		t1.start();
		t2.start();

		Thread.sleep(1000);

		System.out.println("Thread t2 in state: " + t2.getState());
		System.exit(0);
	}

	private static class ThreadInBlockedState implements Runnable {
		@Override
		public void run() {
			doSomethingOnSharedResource();
		}

		private static synchronized void doSomethingOnSharedResource() {
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				System.err.println("Something wrong happened. Error: " + e);
			}
		}
	}
}
