package com.nellpy.visitors;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Visitor implements Runnable {

    private final VisitorsCounter visitorsCounter;


    public Visitor(VisitorsCounter visitorsCounter) {
        this.visitorsCounter = visitorsCounter;
    }


    @Override
    public void run() {
        visitorsCounter.enter();
        try {
            int visitDuration = ThreadLocalRandom.current().nextInt(100, 1000);
            TimeUnit.MILLISECONDS.sleep(visitDuration);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        visitorsCounter.exit();
    }
}
