package com.kokhrimenko.trainings.java.multithreading.intro.run;

import java.util.List;

import com.kokhrimenko.trainings.java.multithreading.intro.payload.Payload;

/**
 * To run a payload in sequential mode.
 *
 * @author kokhrime
 *
 */
public class SequentialRun implements RunMe {

	public void runMe(List<Payload> payloads) {
		if (payloads == null || payloads.isEmpty()) {
			throw new IllegalArgumentException("Trying to run with empty or Null payload. Please check!");
		}
		payloads.forEach(Payload::doSomeWork);
	}

}
