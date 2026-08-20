package com.nellpy.workshops.concurrency.executors.scheduled;

import com.nellpy.workshops.concurrency.threads.callable.HelloStringCallable;
import com.nellpy.workshops.concurrency.threads.runnable.HelloTimeLoggerRunnable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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


    @Test
    public void scheduleAtFixedRate() {
        try(ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()) {
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new HelloTimeLoggerRunnable(), 5, 1, TimeUnit.SECONDS);
            pauseMainThreadExecution();
            scheduledFuture.cancel(true);
        }
    }


    @Test
    public void scheduleWithFixedDelay() {
        try(ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()) {
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new HelloTimeLoggerRunnable(), 5, 1, TimeUnit.SECONDS);
            pauseMainThreadExecution();
            scheduledFuture.cancel(true);
        }
    }


    private static void pauseMainThreadExecution() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}
