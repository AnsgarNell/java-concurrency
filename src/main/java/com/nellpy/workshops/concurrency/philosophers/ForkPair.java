package com.nellpy.workshops.concurrency.philosophers;

import java.util.concurrent.locks.Lock;


public record ForkPair(Lock firstFork, Lock secondFork) {

}
