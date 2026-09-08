package com.nellpy.workshops.concurrency.structured.restaurant;

import org.junit.jupiter.api.Test;

import java.util.concurrent.StructuredTaskScope;

@SuppressWarnings("preview")
class RestaurantTest {

    @Test
    public void reservation() throws InterruptedException {
        Restaurant restaurant1 = new Restaurant("French Cuisine");
        Restaurant restaurant2 = new Restaurant("Italian Cuisine");
        Restaurant restaurant3 = new Restaurant("Burger Paradise");
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>anySuccessfulOrThrow())) {
            scope.fork(restaurant1);
            scope.fork(restaurant2);
            scope.fork(restaurant3);
            String result = scope.join();
            System.out.println();
            System.out.println("Reservation is done at " + result);
        }
    }

}