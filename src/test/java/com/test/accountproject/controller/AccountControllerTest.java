package com.test.accountproject.controller;

import java.util.Collections;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

        mockMvc.perform(MockMvcRequestBuilders.get("/account/rest/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value("Jason"));
    }
}
