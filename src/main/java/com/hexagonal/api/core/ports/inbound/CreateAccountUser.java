package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.User;

public interface CreateAccountUser {
  User execute(String name, String email, String password, String avatar);
}
