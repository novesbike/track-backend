package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.dtos.RegisterUserRequestDTO;
import com.hexagonal.api.application.dtos.RegisterUserResponseDTO;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import com.hexagonal.api.core.ports.inbound.UserAuthServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

  @Mock
  UserAuthServicePort servicePort;

  @InjectMocks
  UserController controller;

  @BeforeEach
  void setUp() {
    when(servicePort.register(any())).thenAnswer(arg -> {
      var userAuth = arg.getArgument(0, UserAuth.class);
      userAuth.setId(UUID.randomUUID());
      return userAuth;
    });
  }

  @Test
  void deveLancarExcecaoAoTentarConverDTOComAtributosNaoPreenchidos() {

    var body = new RegisterUserRequestDTO();

    assertThrows(InvalidAttributeException.class, () -> controller.register(body));
    assertThrows(InvalidAttributeException.class, () -> controller.register(body));
  }


  @Test
  void deveRegistrarNovoUsuarioComSucesso() {
    var role = "ROLE_USER";

    var body = new RegisterUserRequestDTO();
    body.setFullName("Foo");
    body.setEmail("foo@bar.com");
    body.setPassword("FooBar");
    body.setRoles(List.of(role));

    assertDoesNotThrow(() -> {
      var result = controller.register(body);

      assertNotNull(result.getBody().getId());

      assertEquals(body.getEmail(), result.getBody().getEmail());
      assertEquals(body.getFullName(), result.getBody().getFullName());
      assertEquals(body.getRoles(), result.getBody().getRoles());
    });
  }
}