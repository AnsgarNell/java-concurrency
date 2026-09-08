package com.nellpy.workshops.concurrency.structured.police;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("preview")
class PoliceReportTest {

    @Test
    public void policeReport() throws InterruptedException {
        Suspect suspect = fetchSuspect();
        System.out.println(suspect);
    }


    @Test
    public void failingPoliceReport() throws InterruptedException {
        Suspect suspect = fetchSuspectWithException();
        System.out.println(suspect);
    }


    private Suspect fetchSuspect() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())) {
            StructuredTaskScope.Subtask<String> nameSubtask = scope.fork(this::fetchName);
            StructuredTaskScope.Subtask<String> ssnSubtask = scope.fork(this::fetchSSN);
            StructuredTaskScope.Subtask<List<String>> vehiclesSubtask = scope.fork(this::fetchVehicles);
            StructuredTaskScope.Subtask<Integer> arrestsSubtask = scope.fork(this::fetchArrests);
            scope.join();
            return new Suspect(
                    nameSubtask.get(),
                    ssnSubtask.get(),
                    vehiclesSubtask.get(),
                    arrestsSubtask.get());
        }
    }


    private Suspect fetchSuspectWithException() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())) {
            StructuredTaskScope.Subtask<String> nameSubtask = scope.fork(this::fetchName);
            StructuredTaskScope.Subtask<String> ssnSubtask = scope.fork(this::fetchSSNWithException);
            StructuredTaskScope.Subtask<List<String>> vehiclesSubtask = scope.fork(this::fetchVehicles);
            StructuredTaskScope.Subtask<Integer> arrestsSubtask = scope.fork(this::fetchArrests);
            scope.join();
            return new Suspect(
                    nameSubtask.get(),
                    ssnSubtask.get(),
                    vehiclesSubtask.get(),
                    arrestsSubtask.get());
        }
    }


    private String fetchName() throws InterruptedException {
        System.out.println("fetching suspect name");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("suspect name fetched");
        return "John Smith";
    }


    private String fetchSSN() throws InterruptedException {
        System.out.println("fetching suspect SSN");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("suspect SSN fetched");
        return "123-45-6789";
    }


    private String fetchSSNWithException() throws InterruptedException {
        System.out.println("fetching suspect SSN");
        TimeUnit.SECONDS.sleep(3);
        throw new RuntimeException("failed to fetch SSN");
    }


    private List<String> fetchVehicles() throws InterruptedException {
        System.out.println("fetching suspect vehicles");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("suspect vehicles fetched");
        return List.of("TRW 232", "ZRT 582", "OLW 902");
    }


    private int fetchArrests() throws InterruptedException {
        System.out.println("fetching suspect arrests");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("suspect arrests fetched");
        return 12;
    }

}