package com.nellpy.workshops.concurrency.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ConcurrentMapTest {

    private AtomicInteger counter;

    @Test
    public void hashMap() {
        Map<String, String> map = new HashMap<>();
        executeTest(map);
    }


    @Test
    public void concurrentHashMap() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        executeTest(map);
    }


    private void executeTest(Map<String, String> map) {
        counter = new AtomicInteger(0);
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                createThread(i, executorService, map);
            }
        }
        printMap(map);
        Assertions.assertEquals(4, counter.get());
    }


    private void printMap(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }


    private void createThread(int i, ExecutorService executorService, Map<String, String> map) {
        String key = getKey(i);
        executorService.submit(() -> {
            map.computeIfAbsent(key, this::getToken);
        });
    }


    private String getToken(String key) {
        counter.incrementAndGet();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return key + "_token";
    }


    private String getKey(int i) {
        return "key" + i % 4;
    }

}
