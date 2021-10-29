package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.Role;

import java.util.List;

public interface RoleRepositoryPort {
  List<Role> findAll();
}
