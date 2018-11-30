package com.test.accountproject.repository;

import static com.test.accountproject.helper.AccountTestDataHelper.createJasonAccount;
import static org.assertj.core.api.Assertions.assertThat;

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
        Account account = createJasonAccount();

        Account savedAccount = entityManager.persistAndFlush(account);

        Account retrievedAccount = accountRepository.findAll().iterator().next();

        assertThat(retrievedAccount.getFirstName()).isEqualTo(savedAccount.getFirstName());
    }
}