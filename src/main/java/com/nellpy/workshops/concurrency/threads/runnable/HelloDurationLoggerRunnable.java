package com.nellpy.workshops.concurrency.threads.runnable;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;


public class HelloDurationLoggerRunnable implements Runnable {

    @Override
    public void run() {
        Instant start = Instant.now();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Time elapsed: " + timeElapsed + " ms");
    }

}
