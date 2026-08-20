package com.nellpy.workshops.concurrency.visitors;

public class NonSynchronizedVisitorsCounter implements VisitorsCounter {

    private int entered = 0;

    private int left = 0;


    public int getTotal() {
        return entered - left;
    }


    public void enter() {
        entered++;
    }


    public void exit() {
        left++;
    }

}
