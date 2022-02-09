package com.kokhrimenko.trainings.java.multithreading.intro.payload;

import java.util.UUID;

/**
 * Interface to emulate real payload.
 * 
 * @author kokhrime
 *
 */
public interface Payload {

    void doSomeWork();
    
    UUID getId();
}
