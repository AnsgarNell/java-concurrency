package com.nellpy.workshops.concurrency.threads.runnable;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


public class HelloTimeLoggerRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("[" + LocalDateTime.now() + "] Hello from a runnable thread!");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}
