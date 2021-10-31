package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.UserAuthModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserAuthJpaRepository extends JpaRepository<UserAuthModel, UUID> {
  Optional<UserAuthModel> findByEmail(String email);
}
