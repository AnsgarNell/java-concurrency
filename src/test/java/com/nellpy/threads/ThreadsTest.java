package com.nellpy.threads;

import org.junit.jupiter.api.Test;


class ThreadsTest {

    @Test
    public void thread() {
        Thread.ofPlatform().start(new HelloThread());
    }


    @Test
    public void runnable() {
        Thread.ofPlatform().start(new HelloRunnable());
    }

}