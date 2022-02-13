package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * This class demonstrates {@link Thread.join()} method.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class JoinExample implements Runnable {

	public static void main(String args[]) throws Exception {
		Thread t = new Thread(new JoinExample());
		t.start();

		// Waits for 1000ms this thread to die.
		t.join(1000);

		log.info("Joining after 1000 milliseconds");
		log.info("Current thread: {}", t.getName());

		// Checks if this thread is alive
		log.info("Is alive? {}", t.isAlive());
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();

		log.info("Current thread: {}", t.getName());
		log.info("Is Alive? {}", t.isAlive());
	}
}
