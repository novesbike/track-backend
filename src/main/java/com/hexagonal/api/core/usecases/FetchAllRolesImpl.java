package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.ports.inbound.FetchAllRoles;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;

import java.util.List;

public class FetchAllRolesImpl implements FetchAllRoles {

  private final RoleRepositoryPort repository;

  public FetchAllRolesImpl(RoleRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public List<Role> execute() {
    return repository.findAll();
  }
}
