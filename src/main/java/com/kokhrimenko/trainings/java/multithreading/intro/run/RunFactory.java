package com.kokhrimenko.trainings.java.multithreading.intro.run;

/**
 * Utility method to create {@link RunMe} interface based on {@link Mode}.
 * 
 * @author kokhrime
 *
 */
public final class RunFactory {

	private RunFactory() {
		super();
	}

	public static RunMe getRunMe(final Mode mode) {
		if (mode == Mode.SEQUENTIAL) {
			return new SequentialRun();
		}
		return new ParallelRun();
	}

	/**
	 * How to do some work in parallel or sequentially.
	 * 
	 * @author kokhrime
	 *
	 */
	public enum Mode {
		PARALLER,
		SEQUENTIAL;
	}
}
