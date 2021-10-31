package com.hexagonal.api.core.domain.valueobjects;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: implement RFC822: http://www.ex-parrot.com/pdw/Mail-RFC822-Address.html
public class Email {
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
          Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);

  private final String value;

  public Email(String value) {
    if (value == null || value.isBlank()) throw new InvalidAttributeException("Email cannot be blank");
    if (invalidEmailAddress(value)) throw new InvalidAttributeException("Email is invalid");
    this.value = value;
  }

  public boolean isValidEmail(String value) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
    return matcher.find();
  }

  private boolean invalidEmailAddress(String value) {
    return !isValidEmail(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }
}
