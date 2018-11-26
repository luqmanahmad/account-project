package com.test.accountproject.controller;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.accountproject.domain.Account;
import com.test.accountproject.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void should_return_collection_of_accounts() throws Exception {
        final Account account = new Account("Jason", "Simon", 123);

        given(accountService.getAccounts()).willReturn(Collections.singletonList(account));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/account/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value("Jason"));
    }

    @Test
    public void should_create_new_account() throws Exception {
        final Account account = new Account("Jason", "Simon", 123);

        when(accountService.save(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/account/json")
                .content(new ObjectMapper().writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Account has been successfully added."));

    }

    @Test
    public void should_delete_account() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/account/json/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Account successfully deleted"));

    }
}
