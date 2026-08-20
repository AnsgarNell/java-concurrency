package com.nellpy.workshops.concurrency.visitors;

public interface VisitorsCounter {

    void enter();

    void exit();

    int getTotal();

}
