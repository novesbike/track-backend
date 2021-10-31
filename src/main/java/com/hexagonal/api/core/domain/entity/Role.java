package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.Objects;
import java.util.UUID;

public class Role {

  private UUID id;
  private String role;
  private String description;

  public Role(String role) {
    if (role == null || role.isBlank()) throw new InvalidAttributeException("Role cannot be null or empty");
    this.role = role;
  }

  public Role(String role, String desc) {
    if (role == null || role.isBlank()) throw new InvalidAttributeException("Role cannot be null or empty");
    if (desc == null || desc.isBlank()) throw new InvalidAttributeException("Role cannot be null or empty");
    this.role = role;
    this.description = desc;
  }

  public Role(UUID id, String role) {
    this.id = id;
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public String getRole() {
    return role;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role comparable = (Role) o;
    return role.equals(comparable.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role);
  }

  @Override
  public String toString() {
    return getRole();
  }
}
