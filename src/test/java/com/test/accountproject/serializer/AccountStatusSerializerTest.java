package com.test.accountproject.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.test.accountproject.enums.AccountStatus;

public class AccountStatusSerializerTest {

    @Test
    public void createStatusSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AccountStatusModule());


        String json = objectMapper.writeValueAsString(AccountStatus.CREATED);

        assertEquals(
                "{\"message\":\"Account has been successfully added.\"}",
                json);
    }

    @Test
    public void deleteSuccessStatusSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AccountStatusModule());


        String json = objectMapper.writeValueAsString(AccountStatus.DELETE_SUCCESS);

        assertEquals(
                "{\"message\":\"Account successfully deleted\"}",
                json);
    }

    @Test
    public void deleteFailStatusSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AccountStatusModule());


        String json = objectMapper.writeValueAsString(AccountStatus.DELETE_FAIL);

        assertEquals(
                "{\"message\":\"No account found\"}",
                json);
    }

}

class AccountStatusModule extends SimpleModule {
    public AccountStatusModule() {
        this.addSerializer(AccountStatus.class, new AccountStatusSerializer());
    }
}