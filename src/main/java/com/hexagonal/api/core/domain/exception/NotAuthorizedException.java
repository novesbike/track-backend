package com.hexagonal.api.core.domain.exception;

public class NotAuthorizedException extends RuntimeException{

  public NotAuthorizedException() {
    super("Not authorized");
  }

}
