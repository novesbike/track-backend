package com.hexagonal.api.core.ports.outbound;

import com.hexagonal.api.core.domain.entity.User;

public interface SecurityPort {
  User getAuthenticatedUser();
}
