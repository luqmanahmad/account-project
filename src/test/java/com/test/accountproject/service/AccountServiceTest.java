package com.test.accountproject.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.test.accountproject.helper.AccountTestDataHelper.createJasonAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
    public void setUp() {
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void getAccounts() {
        final Account account = createJasonAccount();

        given(accountRepository.findAll()).willReturn(Collections.singletonList(account));

        Collection<Account> accounts = accountService.getAccounts();

        assertThat(accounts.size() == 1);
    }

    @Test
    public void save() {
        final Account account = createJasonAccount();

        given(accountRepository.save(any())).willReturn(account);

        Account savedAccount = accountService.save(account);

        assertNotNull(savedAccount);
        assertThat(savedAccount.getFirstName().equals(account.getFirstName()));

    }

    @Test
    public void deleteSuccess() {
        final Account account = createJasonAccount();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        boolean success = accountService.delete(123);

        assertTrue(success);

        verify(accountRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void deleteNotSuccess() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());
        boolean success = accountService.delete(anyLong());

        assertFalse(success);

        verify(accountRepository, times(0)).deleteById(anyLong());
    }
}