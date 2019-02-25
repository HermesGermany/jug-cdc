package de.hermes.jug.pact.consumer.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class ConsumerConfig {

    @Value("${de.hermes.jug.provider.port}")
    private String providerPort;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
