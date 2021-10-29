package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserAuthJpaRepository extends JpaRepository<UserAuthEntity, UUID> {
  Optional<UserAuthEntity> findByEmail(String email);
}
