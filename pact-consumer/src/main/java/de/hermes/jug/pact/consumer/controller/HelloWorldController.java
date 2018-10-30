package de.hermes.jug.pact.consumer.controller;

import de.hermes.jug.pact.consumer.resource.CustomerDto;
import de.hermes.jug.pact.consumer.service.GetCustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/hello")
public class HelloWorldController {

    private GetCustomerService getCustomerService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String getCustomer(@PathVariable("id") Integer id) {
        CustomerDto customer = getCustomerService.getCustomerById(id);

        String helloWorld = "Hello world,"
                + " " + customer.getFirstname()
                + " " + customer.getLastname()
//                + " " + customer.getAddress() //todo
                ;
        log.info(helloWorld);
        return helloWorld;
    }
}
