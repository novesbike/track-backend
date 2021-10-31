package com.hexagonal.api.core.domain;

import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserProfileTest {


  @Test
  void naoDeveCriarPerfilSemUmNome() {
    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserProfile(null));
    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserProfile("    "));
    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserProfile(null, "my-avatar"));
  }

  @Test
  void oUsuarioNaoTerONomeSetadoParaVazioOuNuloAposOSeuRegistro() {
    var profile = new UserProfile("foo");

    Assertions.assertThrows(InvalidAttributeException.class, () -> profile.setFullName(null));
    Assertions.assertThrows(InvalidAttributeException.class, () -> profile.setFullName("        "));
  }
}