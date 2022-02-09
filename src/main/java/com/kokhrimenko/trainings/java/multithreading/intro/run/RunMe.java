package com.kokhrimenko.trainings.java.multithreading.intro.run;

import java.util.List;

import com.kokhrimenko.trainings.java.multithreading.intro.payload.Payload;

/**
 * Interface to run a payload.
 *
 * @author kokhrime
 *
 */
public interface RunMe {

	void runMe(List<Payload> payloads);
	
}
