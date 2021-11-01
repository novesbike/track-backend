package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
  List<Role> findAll();

  Role save(Role role);

  Optional<Role> findName(String name);
}
