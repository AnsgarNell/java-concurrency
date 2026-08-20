package com.nellpy.executors;

import com.nellpy.threads.HelloThread;
import com.nellpy.threads.callable.HelloNamedCallable;
import com.nellpy.threads.callable.HelloStringCallable;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecutorsTest {

    @Test
    public void singleThreadExecutor() {
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(new HelloThread());
        }
    }


    @Test
    public void callable() throws ExecutionException, InterruptedException {
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<String> future = executorService.submit(new HelloStringCallable());
            String result = future.get();
            System.out.println(result);
        }
    }


    @Test
    public void invokeAllWithSingleThreadExecutor() throws InterruptedException, ExecutionException {
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            List<Future<String>> futures = executorService.invokeAll(getCallables());
            for (Future<String> future : futures) {
                String result = future.get();
                System.out.println(result);
            }
        }
    }


    @Test
    public void invokeAllWithCachedThreadPoolExecutor() throws InterruptedException, ExecutionException {
        try(ExecutorService executorService = Executors.newCachedThreadPool()) {
            List<Future<String>> futures = executorService.invokeAll(getCallables());
            for (Future<String> future : futures) {
                String result = future.get();
                System.out.println(result);
            }
        }
    }


    private static Collection<Callable<String>> getCallables() {
        return List.of(
                new HelloNamedCallable(),
                new HelloNamedCallable(),
                new HelloNamedCallable()
        );
    }

}
