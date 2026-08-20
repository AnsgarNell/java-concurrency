package com.nellpy.executors.scheduled;

import com.nellpy.threads.callable.HelloStringCallable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorsTest {

    @Test
    public void schedule() throws ExecutionException, InterruptedException {
        try(ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()) {
            Future<String> future = scheduledExecutorService.schedule(new HelloStringCallable(), 5, TimeUnit.SECONDS);
            String result = future.get();
            System.out.println(result);
        }
    }

}
