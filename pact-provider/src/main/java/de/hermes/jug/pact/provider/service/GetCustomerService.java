package de.hermes.jug.pact.provider.service;

import de.hermes.jug.pact.provider.conversion.CustomerConverter;
import de.hermes.jug.pact.provider.entity.Customer;
import de.hermes.jug.pact.provider.exception.CustomerNotFoundException;
import de.hermes.jug.pact.provider.repository.CustomerRepository;
import de.hermes.jug.pact.provider.resource.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GetCustomerService {

  private CustomerRepository customerRepository;
  private CustomerConverter conversionService;

  public CustomerDto getCustomerById(int id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (!customer.isPresent()) {
      throw new CustomerNotFoundException();
    }

    CustomerDto customerDto = conversionService.convert(customer.get());
    return customerDto;
  }
}
