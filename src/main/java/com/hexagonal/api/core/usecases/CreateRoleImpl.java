package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.ports.inbound.CreateRole;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;

public class CreateRoleImpl implements CreateRole {

  private final RoleRepositoryPort repository;

  public CreateRoleImpl(RoleRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Role execute(String name, String description) {

    repository.findName(name).ifPresent(function -> {
      throw new BusinessRuleException("Role já existe");
    });

    return repository.save(new Role(name, description));
  }
}
