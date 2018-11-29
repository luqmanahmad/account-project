package com.test.accountproject.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.test.accountproject.enums.AccountStatus;

public class AccountStatusSerializerTest {

    @Test
    public void serialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AccountStatusModule());


        String json = objectMapper.writeValueAsString(AccountStatus.CREATED);

        assertEquals(
                "{\"message\":\"Account has been successfully added.\"}",
                json);
    }

}

class AccountStatusModule extends SimpleModule {
    public AccountStatusModule() {
        this.addSerializer(AccountStatus.class, new AccountStatusSerializer());
    }
}