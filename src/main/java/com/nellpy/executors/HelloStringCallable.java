package com.nellpy.executors;

import java.util.concurrent.Callable;


public class HelloStringCallable implements Callable<String> {

    @Override
    public String call() {
        return "Hello from a callable thread!";
    }

}
