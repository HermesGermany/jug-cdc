package de.hermes.jug.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hermes.jug.pact.consumer.exception.CustomerNotFoundException;
import de.hermes.jug.pact.consumer.resource.CustomerDto;
import de.hermes.jug.pact.consumer.service.GetCustomerService;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerPactTest {

    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2("hermes-jug-provider", "localhost", 7081, this);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    GetCustomerService serviceUnderTest;

    @Pact(consumer = "hermes-jug-consumer", provider = "hermes-jug-provider")
    public RequestResponsePact getCustomer(PactDslWithProvider builder) {

        DslPart body = new PactDslJsonBody()
                .stringType("firstname")
                .stringType("lastname")
//              .stringType("address") //todo
                ;

        return builder
                .given("A customer with id 5")
                .uponReceiving("receiving the customer with id 5")
                .method("GET")
                .path("/customer/5")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @SneakyThrows
    @Pact(consumer = "hermes-jug-consumer", provider = "hermes-jug-provider")
    public RequestResponsePact getCustomerExact(PactDslWithProvider builder) {

        CustomerDto customer = CustomerDto.builder()
                .firstname("Peter")
                .lastname("Hermes")
//              .address("Hamburg") //todo
                .build();

        return builder
                .given("Peter Hermes is a customer with id 1")
                .uponReceiving("receiving the customer (Peter Hermes) with id 1")
                .method("GET")
                .path("/customer/1")
                .willRespondWith()
                .status(200)
                .body(mapper.writeValueAsString(customer), MediaType.APPLICATION_JSON_VALUE)
                .toPact();
    }

    @Pact(consumer = "hermes-jug-consumer", provider = "hermes-jug-provider")
    public RequestResponsePact customerNotFound(PactDslWithProvider builder) {

        return builder
                .given("No customer with id 99")
                .uponReceiving("receiving the customer with id 99")
                .method("GET")
                .path("/customer/99")
                .willRespondWith()
                .status(404)
                .toPact();
    }


    @Test
    @PactVerification(fragment = "getCustomer")
    public void testGetCustomer() {
        assertThatCode(
                () -> serviceUnderTest.getCustomerById(5))
                .doesNotThrowAnyException();
    }

    @Test
    @PactVerification(fragment = "getCustomerExact")
    public void testGetCustomerExact() {
        CustomerDto customerById = serviceUnderTest.getCustomerById(1);
        assert "Peter".equals(customerById.getFirstname());

        assertThatCode(
                () -> serviceUnderTest.getCustomerById(1))
                .doesNotThrowAnyException();
    }

    @Test
    @PactVerification(fragment = "customerNotFound")
    public void testCustomerNotFound() {
        assertThatThrownBy(
                () -> serviceUnderTest.getCustomerById(99))
                .isInstanceOf(CustomerNotFoundException.class);
    }

}
