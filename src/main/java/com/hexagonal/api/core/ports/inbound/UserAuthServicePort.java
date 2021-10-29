package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.UserAuth;

public interface UserAuthServicePort {
  UserAuth register(UserAuth userAuth);
}
