package com.hexagonal.api.core.domain;

import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserAuthTest {


  @Test
  void deveRegistrarNovoUsuarioComSucesso() {

    var email = "email@test.com";
    var password = "pass1234";
    var userProfile = new UserProfile("foo");

    assertDoesNotThrow(() -> {
      var account = new UserAuth(email, password, userProfile);

      assertNotNull(account.getRoles().isEmpty());
      assertEquals(email, account.getEmail());
      assertEquals(password, account.getPassword());
      assertEquals(userProfile.getFullName(), account.getUserProfile().getFullName());

    });
  }

  @Test
  void deveLancarErroQuandoTentarRegistrarUsuarioComAtributosInvalidos() {

    var email = "email@test.com";
    var invalidEmail = "itsInvalidEmail";

    var password = "pass1234";
    var shortPassword = "123";
    var userProfile = new UserProfile("foo");

    assertThrows(InvalidAttributeException.class, () -> new UserAuth(null, password, userProfile));
    assertThrows(InvalidAttributeException.class, () -> new UserAuth("  ", password, userProfile));
    assertThrows(InvalidAttributeException.class, () -> new UserAuth(invalidEmail, password, userProfile));


    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, null, userProfile));
    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, "   ", userProfile));
    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, shortPassword, userProfile));


    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, password, null));

    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, password, null, userProfile));
    assertThrows(InvalidAttributeException.class, () -> new UserAuth(email, password, Collections.emptyList(), userProfile));
  }

  @Test
  void quandoUmUsuarioForRegistradoDeveraEstarPendendeDeConfirmacaoDeEmail() {

    var email = "email@test.com";
    var password = "pass1234";
    var userProfile = new UserProfile("foo");
    var account = new UserAuth(email, password, userProfile);

    assertFalse(account.isActive());
  }

  @Test
  void aposRegistrarUsuarioOEmailDeveraSerConfirmado() {
    // scenery
    var newAccount = new UserAuth("email@test.com", "123@pass", new UserProfile("foo"));
    assertFalse(newAccount.isActive());

    // confirm email method
    newAccount.confirmEmail();

    assertTrue(newAccount.isActive());
  }
}