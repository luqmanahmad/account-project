package com.test.accountproject.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.accountproject.domain.Account;
import com.test.accountproject.enums.AccountStatus;
import com.test.accountproject.exception.AccountNotFoundException;
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

    @PostMapping(value = "/rest/account/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountStatus> createAccount(@RequestBody Account account) {
        accountService.save(account);

        return ResponseEntity.ok(AccountStatus.CREATED);
    }

    @DeleteMapping("/rest/account/json/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        accountService.delete(id);
        return ResponseEntity.ok("Account successfully deleted");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void accountNotFoundHandler(AccountNotFoundException exception) {
    }
}