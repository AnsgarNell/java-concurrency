package com.nellpy.workshops.concurrency.structured.restaurant;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Restaurant implements Callable<String> {

    private final String name;


    public Restaurant(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        printMessage("Checking free tables at " + name);
        delay();
        printMessage("Finished checking free tables at " + name);
        return name;
    }


    private void delay() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(getDuration());
    }


    private int getDuration() {
        return ThreadLocalRandom.current().nextInt(500, 2_000);
    }


    private void printMessage(String message) {
        System.out.println("[" + LocalDateTime.now() + "] " + Thread.currentThread().threadId() + " " + message);
    }

}
