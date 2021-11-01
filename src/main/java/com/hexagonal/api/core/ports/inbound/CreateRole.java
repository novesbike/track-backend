package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Role;

public interface CreateRole {
  Role execute(String role, String description);
}
