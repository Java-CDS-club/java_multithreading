package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * How to start a new thread by extending the {@link Thread} class.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class NewThreadExample extends Thread {

	public static void main(String args[]) {
		new NewThreadExample().start();
	}

	@Override
	public void run() {
		log.info("This is the way how to start a new thread by extending the Thread class.");
	}
}
