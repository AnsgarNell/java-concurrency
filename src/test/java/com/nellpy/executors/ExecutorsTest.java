package com.nellpy.executors;

import com.nellpy.threads.HelloThread;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorsTest {

    @Test
    public void singleThreadExecutor() {
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(new HelloThread());
        }
    }

}
