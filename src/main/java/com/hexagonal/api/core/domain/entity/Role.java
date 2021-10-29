package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.UUID;

public class Role {

  private UUID id;
  private String role;

  public Role(String role) {
    this.role = role;
  }

  public Role(UUID id, String role) {
    this.id = id;
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    if (role == null || role.isBlank()) throw new InvalidAttributeException("");
    this.role = role;
  }
}
