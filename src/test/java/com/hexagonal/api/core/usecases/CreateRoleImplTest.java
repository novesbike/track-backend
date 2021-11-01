package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CreateRoleImplTest {

  @Mock
  RoleRepositoryPort repository;

  @InjectMocks
  CreateRoleImpl createRole;


  @Test
  void deveCriarUmaNovaRoleComSucesso() {

    var name = "ROLE_TRAINER";
    var description = "Treinador";

    when(repository.findName(name)).thenReturn(Optional.empty());
    when(repository.save(any())).thenReturn(new Role(UUID.randomUUID(), name, description));

    createRole.execute(name, description);

  }

  @Test
  void naoDeveCriarRolesComNomesIguais() {

    var name = "ROLE_TRAINER";
    var description = "Treinador";

    var roleInDatabse = new Role(UUID.randomUUID(), name, description);

    when(repository.findName(name)).thenReturn(Optional.of(roleInDatabse));

    assertThrows(BusinessRuleException.class, () -> createRole.execute(name, description));
  }
}