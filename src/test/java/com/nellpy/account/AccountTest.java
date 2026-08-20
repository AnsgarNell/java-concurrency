package com.nellpy.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.nellpy.account.Account.INITIAL_BALANCE;


class AccountTest {

    public static final int N_THREADS = 10;


    @Test
    public void testThreadInterference() {
        Account account = new Account();
        Assertions.assertEquals(INITIAL_BALANCE, account.value());
        try(ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS)) {
            for (int i = 0; i < N_THREADS; i++) {
                executorService.execute(account::increment);
                executorService.execute(account::decrement);
            }
        }
        Assertions.assertEquals(INITIAL_BALANCE, account.value());
    }


    @Test
    public void testThreadInterferenceWithSynchronized() {
        SynchronizedAccount synchronizedAccount = new SynchronizedAccount();
        Assertions.assertEquals(INITIAL_BALANCE, synchronizedAccount.value());
        try(ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS)) {
            for (int i = 0; i < N_THREADS; i++) {
                executorService.execute(synchronizedAccount::increment);
                executorService.execute(synchronizedAccount::decrement);
            }
        }
        Assertions.assertEquals(INITIAL_BALANCE, synchronizedAccount.value());
    }

}