package com.kokhrimenko.trainings.java.multithreading.intro.payload;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PayloadImpl implements Payload {

    private final long sleapingTimeout;
    private final UUID uuid;
    
	public void doSomeWork() {
		System.out.println(String.format("Payload with id: %s started", uuid.toString()));
		try {
			Thread.sleep(sleapingTimeout * 1000);
		} catch (InterruptedException e) {
			System.err.println("Thread sleap was interrupted. Please check. Error: " + e);
		}
		System.out.println(String.format("Payload with id: %s finished", uuid.toString()));
	}

	@Override
	public UUID getId() {
		return getUuid();
	}
}
