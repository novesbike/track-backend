package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleTest {

  @Test
  void naoDeveCriarRoleComAttributosInvalidos() {
    var name = "ROLE_ADMIN";
    var description = "Admin";

    assertThrows(InvalidAttributeException.class, () -> new Role(null, description));
    assertThrows(InvalidAttributeException.class, () -> new Role("    ", description));

    assertThrows(InvalidAttributeException.class, () -> new Role(name, null));
    assertThrows(InvalidAttributeException.class, () -> new Role(name, "   "));
  }

}