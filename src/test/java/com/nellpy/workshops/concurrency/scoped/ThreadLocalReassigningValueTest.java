package com.nellpy.workshops.concurrency.scoped;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ThreadLocalReassigningValueTest {

    public static final String CURRENT_USER = "currentUser";

    private static final ThreadLocal<String> userName = new ThreadLocal<>();


    @Test
    public void reassigningValue() {
        Thread.ofPlatform().start(this::executeAsUser);
    }


    private void executeAsUser() {
        userName.set(CURRENT_USER);
        System.out.println("Executing as user: " + userName.get());
        executeAsAdmin();
        System.out.println("Executing as user: " + userName.get());
        String currentValue = userName.get();
        Assertions.assertEquals(CURRENT_USER, currentValue);
    }


    private void executeAsAdmin() {
        userName.set("admin");
    }

}
