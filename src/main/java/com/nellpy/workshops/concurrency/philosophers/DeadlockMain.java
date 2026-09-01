package com.nellpy.workshops.concurrency.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.nellpy.workshops.concurrency.philosophers.Util.NUMBER_OF_PHILOSOPHERS;
import static com.nellpy.workshops.concurrency.philosophers.Util.createThread;


public class DeadlockMain {

    static void main() {
        List<Lock> forks = new ArrayList<>(NUMBER_OF_PHILOSOPHERS);
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks.add(new ReentrantLock());
        }
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            Philosopher philosopher = new Philosopher(forks.get(i), forks.get((i + 1) % NUMBER_OF_PHILOSOPHERS));
            createThread(philosopher, i);
        }
    }

}
