package com.test.accountproject.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.test.accountproject.enums.AccountStatus;

public class AccountStatusSerializer extends JsonSerializer<AccountStatus> {
    @Override
    public void serialize(AccountStatus accountStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
