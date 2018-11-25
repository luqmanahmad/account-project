package com.test.accountproject.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.accountproject.domain.Account;

@RestController
public class AccountController {

    @GetMapping("/account/rest/json")
    public Collection<Account> getAccounts() {
        return null;
    }
}