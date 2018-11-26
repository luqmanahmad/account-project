package com.test.accountproject.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.test.accountproject.domain.Account;
import com.test.accountproject.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Collection<Account> getAccounts() {
        return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
