package com.kokhrimenko.trainings.java.multithreading.topic4_callable_runnable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * How to use a {@link Callable} interface.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class CallableExample {
	private static final int THREADS_COUNT = 3;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
		List<Future<String>> futureResults = IntStream.range(0, THREADS_COUNT)
				.mapToObj(id -> executorService.submit(new MyCallable()))
				.collect(Collectors.toList());

		futureResults.stream().forEach(future -> {
			try {
				log.info("Returned results from Callable is: {}", future.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error("Something went wrong. Error: " + e);
			}
		});

	}
	
	@Slf4j
	private static class MyCallable implements Callable<String> {
		@Override
		public String call() throws Exception {
			log.info("Callable started and will do some work ~ 1 second");
			Thread.sleep(1_000);
			return String.format("hello random user: %s", UUID.randomUUID().toString());
		}
	}
	
}
