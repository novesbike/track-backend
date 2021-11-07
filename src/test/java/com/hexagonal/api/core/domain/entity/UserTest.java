package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserTest {

  @Test
  void deveRegistrarNovoUsuarioComSucesso() {

    var email = "email@test.com";
    var password = "pass1234";
    var name = "foo";
    var avatar = "avatar.img";
    var role = new Role("ROLE_USER", "Usuário");

    assertDoesNotThrow(() -> {
      var account = new User(name, email, password, avatar, role);

      assertNotNull(account.getRoles().isEmpty());
      assertEquals(email, account.getEmail());
      assertEquals(password, account.getPassword());
      assertEquals(name, account.getName());

    });
  }

  @Test
  void deveLancarErroQuandoTentarRegistrarUsuarioComAtributosInvalidos() {

    var email = "email@test.com";
    var invalidEmail = "itsInvalidEmail";

    var password = "pass1234";
    var shortPassword = "123";

    var name = "foo";
    var avatar = "avatar.img";
    var role = new Role("ROLE_USER", "Usuário");

    // assert email
    assertThrows(InvalidAttributeException.class, () -> new User(null, password, name, avatar, role));
    assertThrows(InvalidAttributeException.class, () -> new User("  ", password, name, avatar, role));
    assertThrows(InvalidAttributeException.class, () -> new User(invalidEmail, password, name, avatar, role));


    // assert password
    assertThrows(InvalidAttributeException.class, () -> new User(email, null, name, avatar, role));
    assertThrows(InvalidAttributeException.class, () -> new User(email, "   ", name, avatar, role));
    assertThrows(InvalidAttributeException.class, () -> new User(email, shortPassword, name, avatar, role));

    // assert name
    assertThrows(InvalidAttributeException.class, () -> new User(email, password, null, avatar, role));
    assertThrows(InvalidAttributeException.class, () -> new User(email, password, "   ", avatar, role));

    // assert Role
    assertThrows(InvalidAttributeException.class, () -> new User(email, password, name, avatar, null));
  }

  @Test
  void quandoUmUsuarioForRegistradoDeveraEstarPendendeDeConfirmacaoDeEmail() {

    var email = "email@test.com";
    var password = "pass1234";
    var name = "foo";
    var avatar = "avatar.img";
    var role = new Role("ROLE_USER", "Usuário");
    var account = new User(name, email, password, avatar, role);

    assertFalse(account.isActive());
  }
}