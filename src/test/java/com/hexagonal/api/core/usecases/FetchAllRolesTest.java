package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FetchAllRolesTest {

  @Mock
  RoleRepositoryPort repository;

  @InjectMocks
  FetchAllRolesImpl fetchAllRoles;

  @Test
  void deveTrazerAListaDeRolesSalvas() {
    var name = "ROLE_TRAINER";
    var description = "Treinador";
    var roleInDatabse = new Role(UUID.randomUUID(), name, description);

    when(repository.findAll()).thenReturn(List.of(roleInDatabse));

    var result = fetchAllRoles.execute();

    Assertions.assertNotNull(result.isEmpty());
  }
}