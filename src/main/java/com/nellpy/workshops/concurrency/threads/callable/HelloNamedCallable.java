package com.nellpy.workshops.concurrency.threads.callable;

import java.util.concurrent.Callable;


public class HelloNamedCallable implements Callable<String> {

    @Override
    public String call() {
        return "Hello from callable thread " + Thread.currentThread().getName() + "!";
    }

}
