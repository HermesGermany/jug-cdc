package de.hermes.jug.pact.consumer.service;

import de.hermes.jug.pact.consumer.configuration.ConsumerConfig;
import de.hermes.jug.pact.consumer.exception.CustomerNotFoundException;
import de.hermes.jug.pact.consumer.resource.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@AllArgsConstructor
@Service
public class GetCustomerService {

    private RestTemplate restTemplate;
    private ConsumerConfig consumerConfig;

    public CustomerDto getCustomerById(int id) {

        CustomerDto customer;
        URI url = URI.create("http://localhost:" + consumerConfig.getProviderPort() + "/customer/" + id);
        try {
            customer = restTemplate.getForObject(url, CustomerDto.class);
        } catch (HttpClientErrorException ex) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
