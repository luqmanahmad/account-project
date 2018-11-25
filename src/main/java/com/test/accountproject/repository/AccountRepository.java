package com.test.accountproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.accountproject.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
