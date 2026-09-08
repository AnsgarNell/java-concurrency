package com.nellpy.workshops.concurrency.structured.brewery;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Brewery implements Callable<Integer> {

    public static final int CAPACITY = 1_000;

    @Override
    public Integer call() throws InterruptedException {
        processInventory();
        return CAPACITY;
    }


    protected void processInventory() throws InterruptedException {
        printMessage("Getting brewery inventory...");
        delay();
        printMessage("Finished getting brewery inventory...");
    }


    protected void delay() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(getDuration());
    }


    private int getDuration() {
        return ThreadLocalRandom.current().nextInt(500, 2_000);
    }


    protected void printMessage(String message) {
        System.out.println("[" + LocalDateTime.now() + "] " + Thread.currentThread().threadId() + " " + message);
    }

}
