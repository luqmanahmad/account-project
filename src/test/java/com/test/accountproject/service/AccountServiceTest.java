package com.test.accountproject.service;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.accountproject.domain.Account;
import com.test.accountproject.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void getAccounts() {
        final Account account = new Account("Jason", "Simon", 123);

        given(accountRepository.findAll()).willReturn(Collections.singletonList(account));

        Collection<Account> accounts = accountService.getAccounts();

        assertThat(accounts.size() == 1);
    }

}