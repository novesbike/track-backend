package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import com.hexagonal.api.core.domain.valueobjects.Email;
import com.hexagonal.api.core.domain.valueobjects.Password;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

  private UUID id;
  private Email email;
  private Password password;
  private String name;
  private String avatar;
  private List<Role> roles = new ArrayList<>();
  private boolean active;
  private Instant createdAt;
  private Instant updatedAt;


  // register common user
  public User(String name, String email, String password, String avatar, Role role) {
    this.email = new Email(email);
    this.password = new Password(password);
    this.avatar = avatar;
    this.active = false;

    setName(name);
    addRole(role);
  }

  // convert
  public User(
          UUID id,
          String email,
          String password,
          String name,
          String avatar,
          List<Role> roles,
          boolean active,
          Instant createdAt,
          Instant updatedAt
  ) {
    this.id = id;
    this.email = new Email(email);
    this.password = new Password(password);
    setName(name);
    this.avatar = avatar;
    this.roles.addAll(roles);
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public User(UUID id) {
    this.id = id;
  }

  public void addRole(Role role) {
    if (role == null)
      throw new InvalidAttributeException("Role cannot be null");

    this.roles.add(role);
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email.getValue();
  }

  public String getPassword() {
    return password.getValue();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isBlank())
      throw new InvalidAttributeException("Name cannot be null or blank");

    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public boolean isActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
