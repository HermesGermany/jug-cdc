package de.hermes.jug.pact.provider.repository;

import de.hermes.jug.pact.provider.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
