package com.hexagonal.api.core.services;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.domain.exception.EmailAlreadyRegisteredException;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserAuthRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserAuthServiceImplTest {

  @Mock
  EmailServicePort emailService;

  @Mock
  UserAuthRepositoryPort repository;

  @Mock
  RoleRepositoryPort roleRepository;

  @InjectMocks
  UserAuthServiceImpl userAuthServicePort;

  @BeforeEach
  void setUp() {
    doNothing().when(emailService).sendConfirmation(any());
  }

  @Test
  void deveRegistrarUmNovoUsuarioComSucesso() {
    var email = "email@test.com";
    var newAccount = new UserAuth(email, "pass1234", new UserProfile("foo"));

    when(repository.findByEmail(email)).thenReturn(Optional.empty());
    when(roleRepository.findAll()).thenReturn(List.of(new Role("ROLE_USER")));
    when(repository.save(newAccount)).thenReturn(newAccount);

    Assertions.assertDoesNotThrow(() -> userAuthServicePort.register(newAccount));
  }

  @Test
  void naoDeveRegistrarComEmailJaCadastrado() {
    var emailAlreadyRegistered = "email@test.com";
    var newAccount = new UserAuth(emailAlreadyRegistered, "pass1234", new UserProfile("foo"));


    when(repository.findByEmail(emailAlreadyRegistered)).thenReturn(Optional.of(
            new UserAuth(emailAlreadyRegistered, "1234pass", new UserProfile("bar"))
    ));

    Assertions.assertThrows(EmailAlreadyRegisteredException.class, () -> userAuthServicePort.register(newAccount));
  }
}