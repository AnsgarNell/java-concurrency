package com.nellpy.threads;

import org.junit.jupiter.api.Test;


class ThreadsTest {

    @Test
    public void thread() {
        Thread.ofPlatform().start(new HelloThread());
    }

}