package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.UserAuth;

import java.util.Optional;

public interface UserAuthRepositoryPort {
  UserAuth save(UserAuth userAuth);
  Optional<UserAuth> findByEmail(String email);
}
