package com.nellpy.workshops.concurrency.scoped;

import org.junit.jupiter.api.Test;

import java.util.concurrent.StructuredTaskScope;

@SuppressWarnings("preview")
public class ScopedValueTest {

    private static final ScopedValue<String> NAME = ScopedValue.newInstance();


    @Test
    public void printValue() {
        ScopedValue.where(NAME, "duke1").run(this::printName);
        ScopedValue.where(NAME, "duke2").run(this::printName);
    }


    @Test
    public void reassignValue() {
        ScopedValue.where(NAME, "original").run(this::reassignName);
    }


    @Test
    public void structuredConcurrency() {
        ScopedValue.where(NAME, "duke").run(() -> {
            try (var scope = StructuredTaskScope.open()) {
                scope.fork(this::printName);
                scope.fork(this::reassignName);
                scope.fork(this::printName);
                scope.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void printName() {
        System.out.println(NAME.get());
    }


    private void reassignName() {
        ScopedValue.where(NAME, "reassigned").run(this::printName);
        printName();
    }

}
