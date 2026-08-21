package com.nellpy.threads;

import com.nellpy.threads.callable.HelloCallable;
import com.nellpy.threads.runnable.HelloRunnable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;


class ThreadsTest {

    @Test
    public void thread() throws InterruptedException {
        Thread thread = Thread.ofPlatform()
                .start(new HelloThread());
        thread.join();
    }


    @Test
    public void runnable() throws InterruptedException {
        Thread thread = Thread.ofPlatform()
                .start(new HelloRunnable());
        thread.join();
    }


    @Test
    public void callable() throws InterruptedException {
        FutureTask<Void> futureTask = new FutureTask<>(new HelloCallable());
        Thread thread = Thread.ofPlatform()
                .start(futureTask);
        thread.join();
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