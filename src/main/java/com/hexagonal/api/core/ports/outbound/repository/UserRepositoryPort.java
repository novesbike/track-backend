package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
  User save(User userAuth);
  Optional<User> findEmail(String email);
  Optional<User> findUserById(UUID idUser);
  List<User> findAll();
}
