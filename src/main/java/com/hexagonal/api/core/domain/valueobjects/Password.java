package com.hexagonal.api.core.domain.valueobjects;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.Objects;

public final class Password {

  private final String value;

  public Password(String value) {
    if (value == null || value.isBlank()) throw new InvalidAttributeException("Password cannot be blank");
    if (value.length() < 4) throw new InvalidAttributeException("Password must contain more than 4 characters");

    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Password password = (Password) o;
    return Objects.equals(value, password.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
