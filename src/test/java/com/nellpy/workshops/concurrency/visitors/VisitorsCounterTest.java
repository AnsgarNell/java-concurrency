package com.nellpy.workshops.concurrency.visitors;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class VisitorsCounterTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000})
    public void nonSynchronizedVisitorsCounter(int visitors) {
        VisitorsCounter visitorsCounter = new NonSynchronizedVisitorsCounter();
        Assertions.assertEquals(0, visitorsCounter.getTotal());
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < visitors; i++) {
                Visitor visitor = new Visitor(visitorsCounter);
                executorService.execute(visitor);
            }
        }
        Assertions.assertEquals(0, visitorsCounter.getTotal());
    }


    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000})
    public void synchronizedVisitorsCounter(int visitors) {
        VisitorsCounter visitorsCounter = new SynchronizedVisitorsCounter();
        Assertions.assertEquals(0, visitorsCounter.getTotal());
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < visitors; i++) {
                Visitor visitor = new Visitor(visitorsCounter);
                executorService.execute(visitor);
            }
        }
        Assertions.assertEquals(0, visitorsCounter.getTotal());
    }

}