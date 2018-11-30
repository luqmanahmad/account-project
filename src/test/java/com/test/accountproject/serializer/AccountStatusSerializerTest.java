package com.test.accountproject.serializer;

import static com.test.accountproject.enums.AccountStatus.CREATED;
import static com.test.accountproject.enums.AccountStatus.DELETE_FAIL;
import static com.test.accountproject.enums.AccountStatus.DELETE_SUCCESS;
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
                getExpectedMessage(CREATED),
                objectMapper.writeValueAsString(CREATED));
    }

    @Test
    public void deleteSuccessStatusSerialize() throws Exception {
        assertEquals(
                getExpectedMessage(DELETE_SUCCESS),
                objectMapper.writeValueAsString(DELETE_SUCCESS));
    }

    @Test
    public void deleteFailStatusSerialize() throws Exception {
        assertEquals(
                getExpectedMessage(DELETE_FAIL),
                objectMapper.writeValueAsString(DELETE_FAIL));
    }

    private String getExpectedMessage(AccountStatus status) {
        return "{\"message\":\"" + status.getMessage() + "\"}";
    }
}

class AccountStatusModule extends SimpleModule {
    public AccountStatusModule() {
        this.addSerializer(AccountStatus.class, new AccountStatusSerializer());
    }
}