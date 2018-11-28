package com.test.accountproject.helper;

import java.util.ArrayList;
import java.util.List;

import com.test.accountproject.domain.Account;

public final class AccountTestDataHelper {

    private AccountTestDataHelper() {}

    public static List<Account> createMultipleAccounts() {
        List<Account> accounts = new ArrayList<>(3);

        accounts.add(createJasonAccount());
        accounts.add(createAccount("Sam", "Lawrence", 1234));
        accounts.add(createAccount("Jon", "Holland", 12345));

        return accounts;
    }

    public static Account createJasonAccount() {
        return createAccount("Jason", "Simon", 123);
    }

    private static Account createAccount(String firstName, String lastName, int accountNumber) {
        return new Account(firstName, lastName, accountNumber);
    }
}
