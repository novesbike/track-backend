package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.RoleJpaRepository;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepositoryPort {

  private final RoleJpaRepository repository;

  @Override
  public List<Role> findAll() {
    return repository.findAll().stream()
            .map(r -> new Role(r.getId(), r.getRole())) //
            .collect(Collectors.toList());
  }
}
