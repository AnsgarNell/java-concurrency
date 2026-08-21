package com.nellpy.workshops.concurrency.threads.virtual;

import com.nellpy.workshops.concurrency.threads.runnable.HelloDurationLoggerRunnable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;


public class VirtualThreadsTest {

    // Adapted from https://github.com/Modern-Concurrency-in-Java/modern-concurrency-java-book/blob/main/src/main/java/ca/bazlur/modern/concurrency/c01/ThreadLimitTest.java
    @Test
    public void classicThreadsLimit() {
        AtomicInteger threadCount = new AtomicInteger(0);
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                Thread.ofPlatform().start(() -> {
                    System.out.println("Created thread: " + threadCount.incrementAndGet());
                    LockSupport.park();
                });
            }
        } catch (OutOfMemoryError error) {
            System.out.println("Reached thread limit: " + threadCount);
            System.err.println(error.getMessage());
            System.exit(1);
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1_000})
    public void newWorkStealingPool(int numberOfThreads) {
        try(ExecutorService executorService = Executors.newWorkStealingPool()) {
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.execute(new HelloDurationLoggerRunnable());
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1_000, 2_000, 10_000})
    public void newFixedThreadPool(int numberOfThreads) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.execute(new HelloDurationLoggerRunnable());
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1_000, 2_000, 10_000, 100_000})
    public void newCachedThreadPool(int numberOfThreads) {
        try(ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.execute(new HelloDurationLoggerRunnable());
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1_000, 2_000, 10_000, 100_000, 1_000_000})
    public void newVirtualThreadPerTaskExecutor(int numberOfThreads) {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.execute(new HelloDurationLoggerRunnable());
            }
        }
    }

}
