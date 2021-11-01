package com.hexagonal.api.core.domain.valueobjects;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

  @Test
  void deveLancarErroQuandoAdicionarUmEmailInvalido() {

    var email = "email@test.com";
    var invalidEmail = "itsInvalidEmail";

    // assert email
    assertThrows(InvalidAttributeException.class, () -> new Email(null));
    assertThrows(InvalidAttributeException.class, () -> new Email("  "));
    assertThrows(InvalidAttributeException.class, () -> new Email(invalidEmail));
  }

  @Test
  void isValidEmail() {

    var email = new Email("thiago_marketingdigital@hotmail.com");

    var result = email.isValidEmail(email.getValue());

    assertTrue(result);

  }
}