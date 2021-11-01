package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<RoleModel, UUID> {
  Optional<RoleModel> findByName(String name);
}
