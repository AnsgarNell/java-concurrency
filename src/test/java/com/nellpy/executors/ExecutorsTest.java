package com.nellpy.executors;

import com.nellpy.threads.HelloThread;
import org.junit.jupiter.api.Test;

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

}
