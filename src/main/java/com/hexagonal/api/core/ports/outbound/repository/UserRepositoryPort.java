package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.User;

import java.util.Optional;

public interface UserRepositoryPort {
  User save(User userAuth);
  Optional<User> findEmail(String email);
}
