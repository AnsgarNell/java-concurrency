package com.nellpy.workshops.concurrency.visitors;

public class SynchronizedVisitorsCounter implements VisitorsCounter {

    private int entered = 0;

    private int left = 0;

    private final Object entryLock = new Object();

    private final Object exitLock = new Object();


    public int getTotal() {
        return entered - left;
    }


    public void enter() {
        synchronized (entryLock) {
            entered++;
        }
    }


    public void exit() {
        synchronized (exitLock) {
            left++;
        }
    }

}
