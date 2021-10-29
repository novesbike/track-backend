package com.hexagonal.api.core.domain;

import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserAuthTest {


  @Test
  void deveValidarOsDadosDoUsuario() {

    var email = "email@test.com";
    var password = "pass1234";
    var userProfile = new UserProfile("foo");

    Assertions.assertDoesNotThrow(() -> {
      var account = new UserAuth(email, password, userProfile);

      Assertions.assertNotNull(account.getRoles().isEmpty());
      Assertions.assertEquals(email, account.getEmail());
      Assertions.assertEquals(password, account.getPassword());
      Assertions.assertEquals(userProfile.getFullName(), account.getUserProfile().getFullName());
    });
  }

  @Test
  void deveLancarErroQuandoTentarRegistrarUsuarioSemEmailOuSenha() {

    var email = "email@test.com";
    var password = "pass1234";
    var userProfile = new UserProfile("foo");

    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserAuth(null, password, userProfile));
    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, null, userProfile));
    Assertions.assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, password, null));
  }


  @Test
  void emailVerified() {
    var newAccount = new UserAuth("email@test.com", "123@pass", new UserProfile("foo"));
    Assertions.assertFalse(newAccount.isActive());

    // confirm email
    newAccount.confirmEmail();
    Assertions.assertTrue(newAccount.isActive());
  }

  @Test
  void deveLancarErroAoTentarSalvarEmailVazioOuNullo() {
    var account = new UserAuth("email@test.com", "123@pass", new UserProfile("foo"));

    Assertions.assertThrows(InvalidAttributeException.class, () -> account.setEmail(null));
    Assertions.assertThrows(InvalidAttributeException.class, () -> account.setEmail("  "));
  }

  @Test
  void deveLancarErroAoTentarSalvarSenhaVazioOuNulla() {
    var account = new UserAuth("email@test.com", "123@pass", new UserProfile("foo"));

    Assertions.assertThrows(InvalidAttributeException.class, () -> account.setPassword(null));
    Assertions.assertThrows(InvalidAttributeException.class, () -> account.setPassword("  "));
    Assertions.assertThrows(InvalidAttributeException.class, () -> account.setPassword("123"));
  }

}