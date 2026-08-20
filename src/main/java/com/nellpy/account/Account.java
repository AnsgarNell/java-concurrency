package com.nellpy.account;

public class Account {

    public static final int INITIAL_BALANCE= 10_000;

    public static final int CHANGE_STEP= 1_000;

    private int balance = INITIAL_BALANCE;


    public void increment() {
        balance = balance + CHANGE_STEP;
    }

    public void decrement() {
        balance = balance - CHANGE_STEP;
    }

    public int value() {
        return balance;
    }

}
