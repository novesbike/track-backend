package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Role {

  private UUID id;
  private String name;
  private String description;
  private Instant createdAt;

  // create role
  public Role(String name, String description) {
    setName(name);
    setDescription(description);
  }

  // mapper role
  public Role(UUID id, String name, String description, Instant createdAt) {
    this.id = id;
    setName(name);
    setDescription(description);
    this.createdAt = createdAt;
  }

  // mapper role
  public Role(UUID id, String name, String description) {
    this.id = id;
    setName(name);
    setDescription(description);
  }

  public UUID getId() {
    return id;
  }

  public String getRole() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    if (name == null || name.isBlank())
      throw new InvalidAttributeException("Role name cannot be null or empty");

    this.name = name;
  }

  public void setDescription(String description) {
    if (description == null || description.isBlank())
      throw new InvalidAttributeException("Role description cannot be null or empty");

    this.description = description;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role comparable = (Role) o;
    return name.equals(comparable.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return getRole();
  }
}
