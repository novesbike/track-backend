package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByEmail(String email);
}
