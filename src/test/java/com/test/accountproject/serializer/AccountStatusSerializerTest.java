package com.test.accountproject.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.test.accountproject.enums.AccountStatus;

public class AccountStatusSerializerTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AccountStatusModule());
    }

    @Test
    public void createStatusSerialize() throws Exception {
        assertEquals(
                "{\"message\":\"Account has been successfully added.\"}",
                objectMapper.writeValueAsString(AccountStatus.CREATED));
    }

    @Test
    public void deleteSuccessStatusSerialize() throws Exception {
        assertEquals(
                "{\"message\":\"Account successfully deleted\"}",
                objectMapper.writeValueAsString(AccountStatus.DELETE_SUCCESS));
    }

    @Test
    public void deleteFailStatusSerialize() throws Exception {
        assertEquals(
                "{\"message\":\"No account found\"}",
                objectMapper.writeValueAsString(AccountStatus.DELETE_FAIL));
    }
}

class AccountStatusModule extends SimpleModule {
    public AccountStatusModule() {
        this.addSerializer(AccountStatus.class, new AccountStatusSerializer());
    }
}