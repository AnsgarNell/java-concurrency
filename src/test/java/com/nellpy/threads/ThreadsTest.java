package com.nellpy.threads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;


class ThreadsTest {

    @Test
    public void thread() {
        Thread.ofPlatform().start(new HelloThread());
    }


    @Test
    public void runnable() {
        Thread.ofPlatform().start(new HelloRunnable());
    }


    @Test
    public void callable() {
        FutureTask<Void> futureTask = new FutureTask<>(new HelloCallable());
        Thread.ofPlatform().start(futureTask);
    }


    @Test
    public void threadStates() throws InterruptedException {
        Thread thread = Thread.ofPlatform().unstarted(new HelloThread());
        System.out.println("Thread is in state: " + thread.getState());
        thread.start();
        System.out.println("Thread is in state: " + thread.getState());
        thread.join();
        System.out.println("Thread is in state: " + thread.getState());
    }

}