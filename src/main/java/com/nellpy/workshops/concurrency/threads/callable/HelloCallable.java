package com.nellpy.workshops.concurrency.threads.callable;

import java.util.concurrent.Callable;


public class HelloCallable implements Callable<Void> {

    @Override
    public Void call() {
        System.out.println("Hello from a callable thread!");
        return null;
    }

}
