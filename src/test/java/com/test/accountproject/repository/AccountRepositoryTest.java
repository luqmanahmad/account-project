package com.test.accountproject.repository;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.accountproject.domain.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findAll() {
        Account account = new Account("Jason", "Simon", 123);

        Account savedAccount = entityManager.persistAndFlush(account);

        Account retrievedAccount = accountRepository.findAll().iterator().next();

        Assertions.assertThat(retrievedAccount.getFirstName()).isEqualTo(savedAccount.getFirstName());
    }
}