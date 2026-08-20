package com.nellpy.account;

public class SynchronizedAccount {

    public static final int INITIAL_BALANCE= 10_000;
    public static final int CHANGE_STEP= 1_000;

    private int balance = INITIAL_BALANCE;

    public synchronized void increment() {
        balance = balance + CHANGE_STEP;
    }

    public synchronized void decrement() {
        balance = balance - CHANGE_STEP;
    }

    public synchronized int value() {
        return balance;
    }

}
