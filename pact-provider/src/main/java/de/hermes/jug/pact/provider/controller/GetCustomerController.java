package de.hermes.jug.pact.provider.controller;

import de.hermes.jug.pact.provider.resource.CustomerDto;
import de.hermes.jug.pact.provider.service.GetCustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/customer")
public class GetCustomerController {

  private GetCustomerService getCustomerService;

  @GetMapping(path = "/{id}")
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") int id) {
    log.info("Getting customer {}", id);
    CustomerDto customer = getCustomerService.getCustomerById(id);
    return ResponseEntity.ok(customer);
  }
}
