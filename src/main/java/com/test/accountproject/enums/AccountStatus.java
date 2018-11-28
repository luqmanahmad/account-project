package com.test.accountproject.enums;

public enum AccountStatus {

    CREATED("Account has been successfully added.");

    private String message;

    AccountStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
