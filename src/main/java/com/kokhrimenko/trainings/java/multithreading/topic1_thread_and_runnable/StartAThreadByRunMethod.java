package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * What is going to be happen, if we call {@link Thread.run()} method.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class StartAThreadByRunMethod {

	public static void main(String[] args) {
		log.info("Before calling Thread.run() method");
		
		new Thread(new NewThread()).run();

		log.info("After calling Thread.run() method");
	}
	
	private static class NewThread implements Runnable {

		@Override
		public void run() {
			log.info("Start of a new method logic inside a thread");
			try {
				Thread.sleep(2_000);
			} catch (InterruptedException e) {
				log.error("Something wrong happened. Error: {}", e);
			}
			log.info("End of a new method logic inside a thread");
		}

	}
}
