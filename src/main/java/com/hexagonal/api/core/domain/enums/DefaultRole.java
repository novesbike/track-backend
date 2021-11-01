package com.hexagonal.api.core.domain.enums;

public enum DefaultRole {

  ROLE_USER,
  ROLE_TRAINER,
  ROLE_ADMIN;

  @Override
  public String toString() {
    return ROLE_USER.toString();
  }
}
