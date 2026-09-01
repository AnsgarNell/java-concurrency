package com.nellpy.workshops.concurrency.philosophers;

public class Util {

    public static final int NUMBER_OF_PHILOSOPHERS = 5;

    public static void createThread(Philosopher philosopher, int i) {
        Thread thread = Thread.ofPlatform()
                .unstarted(philosopher);
        thread.setName("Philosopher " + (i + 1));
        thread.start();
    }

}
