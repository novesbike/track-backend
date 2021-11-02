package com.hexagonal.api.core.domain.valueobjects;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

  @Test
  void deveLancarErroQuandoAdicionarUmaSenhaInvalida() {
    assertThrows(InvalidAttributeException.class, () -> new Password(null));
    assertThrows(InvalidAttributeException.class, () -> new Password("   "));
    assertThrows(InvalidAttributeException.class, () -> new Password("123"));
  }

}