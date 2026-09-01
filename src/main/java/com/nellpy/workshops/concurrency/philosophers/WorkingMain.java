package com.nellpy.workshops.concurrency.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.nellpy.workshops.concurrency.philosophers.Util.NUMBER_OF_PHILOSOPHERS;
import static com.nellpy.workshops.concurrency.philosophers.Util.createThread;


public class WorkingMain {

    static void main() {
        List<Lock> forks = new ArrayList<>(NUMBER_OF_PHILOSOPHERS);
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks.add(new ReentrantLock());
        }
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            ForkPair forkPair = new ForkPair(forks.get(i), forks.get((i + 1) % NUMBER_OF_PHILOSOPHERS));
            Philosopher philosopher = getPhilosopher(i, forkPair);
            createThread(philosopher, i);
        }
    }


    private static Philosopher getPhilosopher(int i, ForkPair forkPair) {
        if (i == 0) {
            return new SwitchedPhilosopher(forkPair);
        } else {
            return new Philosopher(forkPair);
        }
    }

}
