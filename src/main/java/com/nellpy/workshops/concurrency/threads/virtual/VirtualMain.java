package com.nellpy.workshops.concurrency.threads.virtual;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;


public class VirtualMain {

    // Adapted from https://github.com/Modern-Concurrency-in-Java/modern-concurrency-java-book/blob/main/src/main/java/ca/bazlur/modern/concurrency/c01/ThreadLimitTest.java
    static void main() {
        AtomicInteger threadCount = new AtomicInteger(0);
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                Thread.ofVirtual().start(() -> {
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

}
