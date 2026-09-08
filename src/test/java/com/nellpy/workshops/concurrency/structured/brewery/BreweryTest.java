package com.nellpy.workshops.concurrency.structured.brewery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

import static com.nellpy.workshops.concurrency.structured.brewery.Brewery.CAPACITY;


@SuppressWarnings("preview")
class BreweryTest {

    private static final int BREWERY_NUMBER = 10;

    @Test
    public void getInventory() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<Integer>allSuccessfulOrThrow())) {
            forkSubtasks(scope);
            int inventory = joinSubtasks(scope);
            Assertions.assertEquals(BREWERY_NUMBER * CAPACITY, inventory);
        }
    }


    @Test
    public void getInventoryWithFailure() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<Integer>allSuccessfulOrThrow())) {
            forkSubtasks(scope);
            scope.fork(new FailingBrewery());
            scope.join();
        }
    }


    private static void forkSubtasks(StructuredTaskScope<Integer, List<Integer>> scope) {
        for (int i = 0; i < BREWERY_NUMBER; i++) {
            scope.fork(new Brewery());
        }
    }


    private static int joinSubtasks(StructuredTaskScope<Integer, List<Integer>> scope) throws InterruptedException {
        return scope.join()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}