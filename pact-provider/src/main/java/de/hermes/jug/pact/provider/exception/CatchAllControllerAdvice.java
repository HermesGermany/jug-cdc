package de.hermes.jug.pact.provider.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CatchAllControllerAdvice {

  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public void customerNotFound(CustomerNotFoundException ex) {
    log.debug("Customer not found", ex);
  }
}
