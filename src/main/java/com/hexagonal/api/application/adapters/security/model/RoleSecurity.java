package com.hexagonal.api.application.adapters.security.model;

import com.hexagonal.api.core.domain.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.UUID;

public class RoleSecurity extends Role implements GrantedAuthority {

  public RoleSecurity(UUID id, String name, String description, Instant createdAt) {
    super(id, name, description, createdAt);
  }

  public RoleSecurity(Role role) {
    this(role.getId(), role.getName(), role.getDescription(), role.getCreatedAt());
  }

  @Override
  public String getAuthority() {
    return this.getName();
  }
}
