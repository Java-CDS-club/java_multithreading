package com.kokhrimenko.trainings.java.multithreading.intro.run;

import java.util.List;
import java.util.stream.Collectors;

import com.kokhrimenko.trainings.java.multithreading.intro.payload.Payload;

/**
 * To run a payload in multithreading mode.
 * 
 * @author kokhrime
 *
 */
public class ParallelRun implements RunMe {

	public void runMe(List<Payload> payloads) {
		if (payloads == null || payloads.isEmpty()) {
			throw new IllegalArgumentException("Trying to run with empty or Null payload. Please check!");
		}
		List<Thread> threads = payloads.stream()
					.map(payload -> {
						Thread thread = new Thread(() -> payload.doSomeWork());
						thread.start();
						return thread;
					})
					.collect(Collectors.toList());
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println(String.format("Thread with id: %s was interrupted. Error: %s", thread.getName(), e));
			}
		});
	}

}
