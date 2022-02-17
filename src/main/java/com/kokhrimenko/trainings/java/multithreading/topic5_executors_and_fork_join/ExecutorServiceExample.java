package com.kokhrimenko.trainings.java.multithreading.topic5_executors_and_fork_join;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link ExecutorService} example.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class ExecutorServiceExample {
	private static final int POOL_SIZE = 5;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

		List<Future<String>> results = IntStream.range(0, POOL_SIZE + 3)
				.mapToObj(id -> executorService.submit(new MyCallable()))
				.collect(Collectors.toList());

		results.forEach(result -> {
			try {
				log.info(result.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error("Something went wrong. Error: {}", e);
			}
		});
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}
	
	@Slf4j
	private static class MyCallable implements Callable<String> {
		private final Instant startTime;
		private final String name;

		public MyCallable() {
			this.startTime = Instant.now();
			this.name = UUID.randomUUID().toString();
		}
		
		@Override
		public String call() throws Exception {
			log.info("Thread: {} started", name);
			Thread.sleep(1_000);
			log.info("Thread: {} finished", name);
			return String.format("Thread: %s done it job in: %d secconds", name, Duration.between(startTime, Instant.now()).getSeconds());
		}
		
	}
	
}
