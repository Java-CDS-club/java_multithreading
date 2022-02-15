package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * Example with the volatile keyword.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class VolatileExample {
	private static int number;
	private static boolean ready;

	private static class Reader extends Thread {
		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}

			log.info("Number is: {}", number);
		}
	}

	public static void main(String[] args) {
		new Reader().start();
		number = 42;
		ready = true;
	}
}
