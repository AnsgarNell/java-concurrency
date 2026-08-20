package com.nellpy.visitors;

public interface VisitorsCounter {

    void enter();

    void exit();

    int getTotal();

}
