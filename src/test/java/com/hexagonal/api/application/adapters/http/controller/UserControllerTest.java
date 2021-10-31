package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.dtos.RegisterUserRequestDTO;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

  UserController controller;

  @Test
  void deveLancarExcecaoAoTentarConverDTOComAtributosNaoPreenchidos() {

    RegisterUserRequestDTO body = new RegisterUserRequestDTO();

    assertThrows(InvalidAttributeException.class, () -> controller.register(body));
    assertThrows(InvalidAttributeException.class, () -> controller.register(body));
  }
}