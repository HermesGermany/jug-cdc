package de.hermes.jug.pact.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import de.hermes.jug.pact.provider.entity.Customer;
import de.hermes.jug.pact.provider.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@RunWith(SpringRestPactRunner.class)
@Provider("hermes-jug-provider")
@PactBroker(host = "localhost", port = "80")
//@PactFolder(value = "pacts")
@VerificationReports({"console", "markdown", "json"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProviderPactTest {

    @TestTarget
    public final HttpTarget target = new HttpTarget(7081);

    @Autowired
    private CustomerRepository customerRepository;

    @State("A customer with id 5")
    public void setupCustomerWithId5() {
        log.info("Create state: 'A customer with id 5'");

        Customer customer = Customer.builder()
                .id(5)
                .firstname("Helga")
                .lastname("Schmidt")
                .address("Hamburg")
                .build();

        customerRepository.save(customer);
    }

    @State("Peter Hermes is a customer with id 1")
    public void setupExactCustomerWithId1() {
        log.info("Create state: 'Peter Hermes is a customer with id 1'");

        Customer customer = Customer.builder()
                .id(1)
                .firstname("Peter")
                .lastname("Hermes")
                .address("Hamburg")
                .build();

        customerRepository.save(customer);
    }

    @State("No customer with id 99")
    public void setupNoCustomerWithId99() {
        log.info("Create state: 'No customer with id 99'");
    }

}
