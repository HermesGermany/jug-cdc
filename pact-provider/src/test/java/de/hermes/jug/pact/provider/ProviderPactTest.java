package de.hermes.jug.pact.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import de.hermes.jug.pact.provider.exception.CustomerNotFoundException;
import de.hermes.jug.pact.provider.resource.CustomerDto;
import de.hermes.jug.pact.provider.service.GetCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;


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

  @MockBean
  private GetCustomerService getCustomerServiceMock;

  @State("A customer with id 5")
  public void setupCustomerWithId5() {
    log.info("Create state: 'A customer with id 5'");

    CustomerDto customer = CustomerDto.builder()
            .firstname("Helga")
            .lastname("Schmidt")
//          .address("Hamburg") //todo
            .build();

    when(getCustomerServiceMock.getCustomerById(5)).thenReturn(customer);
  }

  @State("Peter Hermes is a customer with id 1")
  public void setupExactCustomerWithId1() {
    log.info("Create state: 'Peter Hermes is a customer with id 1'");

    CustomerDto customer = CustomerDto.builder()
            .firstname("Peter")
            .lastname("Hermes")
            //              .address("Hamburg") //todo
            .build();

    when(getCustomerServiceMock.getCustomerById(1)).thenReturn(customer);
  }

  @State("No customer with id 99")
  public void setupNoCustomerWithId99() {
    log.info("Create state: 'No customer with id 99'");

    when(getCustomerServiceMock.getCustomerById(99))
            .thenThrow(new CustomerNotFoundException());
  }

}
