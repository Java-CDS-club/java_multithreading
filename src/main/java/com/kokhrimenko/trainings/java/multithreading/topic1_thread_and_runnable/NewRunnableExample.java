package com.kokhrimenko.trainings.java.multithreading.topic1_thread_and_runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * How to start a new thread by implementing {@link Runnable} interface.
 * 
 * @author kokhrime
 *
 */
@Slf4j
public class NewRunnableExample implements Runnable {

	public static void main(String args[]) {
		new Thread(new NewRunnableExample()).start();
	}
	
	@Override
	public void run() {
		log.info("This is the way how to start a new thread by implementing the Runnable interface.");
	}
}
