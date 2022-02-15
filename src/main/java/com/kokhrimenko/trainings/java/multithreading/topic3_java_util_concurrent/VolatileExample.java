package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * Example with the volatile keyword.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class VolatileExample extends Thread {
	private /*volatile*/ boolean keepRunning = true;

	@Override
	public void run() {
		long count = 0;
		while (keepRunning) {
			count++;
		}

		log.info("Thread terminated: {}", count);
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileExample volatileExample = new VolatileExample();
		volatileExample.start();
		Thread.sleep(1_000);
		log.info("After sleeping in the main method");
		volatileExample.keepRunning = false;
		volatileExample.join();
		log.info("keepRunning set to: " + volatileExample.keepRunning);
	}
}
