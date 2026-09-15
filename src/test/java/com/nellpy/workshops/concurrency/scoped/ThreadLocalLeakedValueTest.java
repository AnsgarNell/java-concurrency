package com.nellpy.workshops.concurrency.scoped;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalLeakedValueTest {

    public static final String DOCUMENT_ID = "123456";

    private static final ThreadLocal<String> documentId = new ThreadLocal<>();


    @Test
    public void leakingValue() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.submit(this::successfulDocumentCreation);
            executor.submit(this::printDocumentId);
            executor.submit(this::erroneousDocumentCreation);
            executor.submit(this::printDocumentId);
        }
    }


    private void successfulDocumentCreation() {
        documentId.set(DOCUMENT_ID);
    }


    private void erroneousDocumentCreation() {
        throw new RuntimeException("Document creation failed");
    }


    private void printDocumentId() {
        System.out.println("Document ID: " + documentId.get());
        Assertions.assertEquals(DOCUMENT_ID, documentId.get());
    }

}
