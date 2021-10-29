package com.hexagonal.api.core.domain.exception;

public class InvalidAttributeException extends RuntimeException {

  public InvalidAttributeException(String message) {
    super(message);
  }
}
