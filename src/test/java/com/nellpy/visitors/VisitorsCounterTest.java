package com.nellpy.visitors;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class VisitorsCounterTest {

    public static final int VISITORS = 10_000;


    @Test
    public void visitorsCounter() {
        VisitorsCounter visitorsCounter = new VisitorsCounter();
        Assertions.assertEquals(0, visitorsCounter.getTotal());
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < VISITORS; i++) {
                Visitor visitor = new Visitor(visitorsCounter);
                executorService.execute(visitor);
            }
        }
        Assertions.assertEquals(0, visitorsCounter.getTotal());
    }

}