package com.test.accountproject.domain;

public class Account {
    private final String firstName;
    private final String lastName;
    private final int accountNumber;

    public Account(String firstName, String lastName, int accountNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
