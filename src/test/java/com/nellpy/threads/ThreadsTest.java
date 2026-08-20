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

}