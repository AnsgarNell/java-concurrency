package com.nellpy.visitors;

public class VisitorsCounter {

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
