package com.kokhrimenko.trainings.java.multithreading.intro.payload;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@Slf4j
public class PayloadImpl implements Payload {

    private final long sleapingTimeout;
    private final UUID uuid;
    
	public void doSomeWork() {
		log.info("Payload with id: {} started", uuid.toString());
		try {
			Thread.sleep(sleapingTimeout * 1000);
		} catch (InterruptedException e) {
			log.error("Thread sleap was interrupted. Please check. Error: {}", e);
		}
		log.info("Payload with id: {} finished", uuid.toString());
	}

	@Override
	public UUID getId() {
		return getUuid();
	}
}
