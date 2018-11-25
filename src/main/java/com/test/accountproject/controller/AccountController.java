package com.test.accountproject.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.accountproject.domain.Account;
import com.test.accountproject.service.AccountService;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/rest/account/json")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }
}