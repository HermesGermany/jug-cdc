package de.hermes.jug.pact.provider.setup;

import de.hermes.jug.pact.provider.entity.Customer;
import de.hermes.jug.pact.provider.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProviderDataLoader implements CommandLineRunner {

  private CustomerRepository customerRepository;

  @Override
  public void run(String... args) {
    customerRepository.save(new Customer(1, "Peter", "Hermes", "Hamburg"));
    customerRepository.save(new Customer(2, "Maria", "Schmidt", "Hamburg"));
    customerRepository.save(new Customer(3, "Lisa", "Meyer", "Hamburg"));
    customerRepository.save(new Customer(4, "Petra", "Müller", "Hamburg"));
  }
}
