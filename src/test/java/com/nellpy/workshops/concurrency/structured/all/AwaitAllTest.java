package com.nellpy.workshops.concurrency.structured.all;

import org.junit.jupiter.api.Test;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("preview")
public class AwaitAllTest {

    private static final int COUNT = 10;

    @Test
    public void awaitAll() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAll())) {
            for (int i = 0; i < COUNT; i++) {
                scope.fork(() -> {
                    doSomething();
                    return null;
                });
            }
            scope.fork(() -> {
                doSomethingAndFail();
                return null;
            });
            scope.join();
        }
    }


    private void doSomething() throws InterruptedException {
        System.out.println("Doing something...");
        delay();
        System.out.println("Finished doing something");
    }


    private void doSomethingAndFail() {
        System.out.println("Doing something which will fail...");
        throw new RuntimeException("Something went wrong");
    }


    private void delay() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(getDuration());
    }


    private int getDuration() {
        return ThreadLocalRandom.current().nextInt(2_000, 5_000);
    }

}
