package de.hermes.jug.pact.provider.conversion;

import de.hermes.jug.pact.provider.entity.Customer;
import de.hermes.jug.pact.provider.resource.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerDto> {
  @Override
  public CustomerDto convert(Customer customer) {
    return CustomerDto.builder()
            .firstname(customer.getFirstname())
            .lastname(customer.getLastname())
            //                .address(customer.getAddress()) //todo
            .build();
  }
}
