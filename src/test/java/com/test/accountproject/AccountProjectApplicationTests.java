package com.test.accountproject;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.accountproject.domain.Account;
import com.test.accountproject.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountProjectApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

    @Test
    public void should_return_collection_of_accounts() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/rest/account/json", Collection.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertTrue(response.getBody().size() > 0);
    }

}

@Component
class TestDataCLR implements CommandLineRunner {
    private AccountRepository accountRepository;

    public TestDataCLR(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        accountRepository.save(new Account("Jason", "Zinch", 123));
        accountRepository.save(new Account("Sam", "Lawrence", 1234));
        accountRepository.save(new Account("Jon", "Holland", 12345));
    }
}