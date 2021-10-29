package com.hexagonal.api.core.domain.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{

  public EmailAlreadyRegisteredException() {
    super("There is already a registered user with this email.");
  }

}
