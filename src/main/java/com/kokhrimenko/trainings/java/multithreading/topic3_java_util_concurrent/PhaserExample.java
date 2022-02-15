package com.kokhrimenko.trainings.java.multithreading.topic3_java_util_concurrent;

import java.util.concurrent.Phaser;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Phaser} example.
 *
 * @author kokhrime
 *
 */
@Slf4j
public class PhaserExample {

	public static void main(String[] args) throws InterruptedException {
		final Phaser phaser = new Phaser(1);

		new MyThread(phaser).start();
		new MyThread(phaser).start();
		new MyThread(phaser).start();
		phaser.arriveAndAwaitAdvance();
		phaser.arriveAndDeregister();
	}
	
	@Slf4j
	private static class MyThread extends Thread {
		private final Phaser phaser;

		public MyThread(final Phaser phaser) {
			this.phaser = phaser;
			this.phaser.register();
		}
		
		@Override
		public void run() {
			log.info("Thread {} waiting for others to reach this stage", getName());
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				log.error("Thread: {}, something went wrong. Error: {}", getName(), e);
			}
			log.info("Thread {} finished it long running work", getName());
			phaser.arriveAndAwaitAdvance();
			log.info("Thread {} proceeding in phase {}", getName(), phaser.getPhase());
			phaser.arriveAndDeregister();
		}
	}
	
}
