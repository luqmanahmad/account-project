package com.test.accountproject;

import java.util.Collection;

import static com.test.accountproject.enums.AccountStatus.CREATED;
import static com.test.accountproject.enums.AccountStatus.DELETE_SUCCESS;
import static com.test.accountproject.helper.AccountTestDataHelper.createJasonAccount;
import static com.test.accountproject.helper.AccountTestDataHelper.createMultipleAccounts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.accountproject.domain.Account;
import com.test.accountproject.enums.AccountStatus;
import com.test.accountproject.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountProjectE2ETests {

	@Autowired
	TestRestTemplate testRestTemplate;

    @Test
    public void should_return_collection_of_accounts() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/rest/account/json", Collection.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void should_create_new_account() {
        Account account = createJasonAccount();

        ResponseEntity<String> response = testRestTemplate.postForEntity("/rest/account/json", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().equals(getExpectedMessage(CREATED)));
    }

    @Test
    public void should_delete_account() {
        ResponseEntity<String> response = testRestTemplate.exchange("/rest/account/json/1",
                                                         HttpMethod.DELETE,
                                                         new HttpEntity<>(DELETE_SUCCESS),
                                                         String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().equals(getExpectedMessage(DELETE_SUCCESS)));
    }

    private String getExpectedMessage(AccountStatus status) {
        return "{\"message\":\"" + status.getMessage() + "\"}";
    }
}

@Component
class TestDataCLR implements CommandLineRunner {
    private AccountRepository accountRepository;

    public TestDataCLR(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        createMultipleAccounts().forEach(account -> accountRepository.save(account));
    }
}