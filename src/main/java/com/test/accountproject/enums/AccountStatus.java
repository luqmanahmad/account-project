package com.test.accountproject.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.accountproject.serializer.AccountStatusSerializer;

@JsonSerialize(using=AccountStatusSerializer.class)
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
