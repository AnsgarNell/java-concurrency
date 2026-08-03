package com.nellpy;

public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }


    static void main() throws InterruptedException {
        Thread thread = new HelloThread();
        thread.start();
        thread.join();
    }
}
