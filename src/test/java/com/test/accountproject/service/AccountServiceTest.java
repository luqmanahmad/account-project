package com.test.accountproject.service;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.accountproject.domain.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService accountService;

    @Test
    public void getAccounts() {
        Collection<Account> accounts = accountService.getAccounts();

        assertThat(accounts.size() == 1);
    }

}
