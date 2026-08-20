package com.nellpy.threads.runnable;

public class HelloRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from a runnable thread!");
    }

}
