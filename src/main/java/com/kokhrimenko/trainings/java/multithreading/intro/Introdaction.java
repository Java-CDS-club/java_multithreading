package com.kokhrimenko.trainings.java.multithreading.intro;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.kokhrimenko.trainings.java.multithreading.intro.payload.Payload;
import com.kokhrimenko.trainings.java.multithreading.intro.payload.PayloadImpl;
import com.kokhrimenko.trainings.java.multithreading.intro.run.RunFactory;
import com.kokhrimenko.trainings.java.multithreading.intro.run.RunFactory.Mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Introdaction {
	private static final int PAYLOAD_COUNT = 20;
	private static final long DEFAULT_TIMEOUT = 1;

    public static void main(String[] args) {
    	final Mode mode = Mode.PARALLER;

        List<Payload> payloads = IntStream.range(0, PAYLOAD_COUNT)
        			.mapToObj(index -> new PayloadImpl(DEFAULT_TIMEOUT, UUID.randomUUID()))
        			.collect(Collectors.toList());

        Instant start = Instant.now();
        RunFactory.getRunMe(mode).runMe(payloads);
        log.info("To do all payloads took: {} seconds", Duration.between(start, Instant.now()).toSeconds());
    }
    
}
