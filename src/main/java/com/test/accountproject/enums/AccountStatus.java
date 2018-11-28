package com.test.accountproject.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.accountproject.serializer.AccountStatusSerializer;

@JsonSerialize(using=AccountStatusSerializer.class)
public enum AccountStatus {

    CREATED("Account has been successfully added."),
    DELETE_SUCCESS("Account successfully deleted"),
    DELETE_FAIL("No account found");

    private String message;

    AccountStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
