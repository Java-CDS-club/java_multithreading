package com.kokhrimenko.trainings.java.multithreading.topic5_executors_and_fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Example of how to use a {@link ForkJoinPool} with a {@link RecursiveAction}.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class ForkJoinTaskExample {

	public static void main(String[] args) {
		MyRecursiveAction myRecursiveAction = new MyRecursiveAction("ddddffffgggghhhh");
        ForkJoinPool.commonPool().invoke(myRecursiveAction);

        log.info("All works were done: {}", myRecursiveAction.isDone());
	}
	
	@Slf4j
	@RequiredArgsConstructor
	private static class MyRecursiveAction extends RecursiveAction {
		private static final long serialVersionUID = -6370506570544837087L;

		private static final int THRESHOLD = 5;

		private final String workLoad;

		@Override
	    protected void compute() {
	        if (workLoad.length() > THRESHOLD) {
	            ForkJoinTask.invokeAll(createSubtasks());
	        } else {
	            processing(workLoad);
	        }
	    }

		private List<MyRecursiveAction> createSubtasks() {
	        List<MyRecursiveAction> subtasks = new ArrayList<>();

	        String partOne = workLoad.substring(0, workLoad.length() / 2);
	        String partTwo = workLoad.substring(workLoad.length() / 2, workLoad.length());

	        subtasks.add(new MyRecursiveAction(partOne));
	        subtasks.add(new MyRecursiveAction(partTwo));

	        return subtasks;
	    }

		private void processing(String work) {
	        String result = work.toUpperCase();
	        log.info("This result - ({}) - was processed by {}", result, Thread.currentThread().getName());
	    }
	}
	
}
