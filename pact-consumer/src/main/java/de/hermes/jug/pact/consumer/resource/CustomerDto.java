package de.hermes.jug.pact.consumer.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
  private String firstname;
  private String lastname;
//      private String address; //todo
}
