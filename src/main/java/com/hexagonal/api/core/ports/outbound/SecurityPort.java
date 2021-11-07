package com.hexagonal.api.core.ports.outbound;

import com.hexagonal.api.core.domain.entity.User;

import java.util.Optional;

public interface SecurityPort {
  Optional<User> getAuthenticatedUser();
}
