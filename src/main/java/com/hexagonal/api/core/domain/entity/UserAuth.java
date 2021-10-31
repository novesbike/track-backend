package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import com.hexagonal.api.core.domain.valueobjects.Email;
import com.hexagonal.api.core.domain.valueobjects.Password;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserAuth {

  private UUID id;
  private Email email;
  private Password password;
  private UserProfile userProfile;
  private List<Role> roles = new ArrayList<>();
  private boolean active;


  public UserAuth(String email, String password, String fullName) {
    this.email = new Email(email);
    this.password = new Password(password);
    this.userProfile = new UserProfile(fullName);

    addRole("ROLE_USER");
  }

  public UserAuth(String email, String password, List<Role> roles, UserProfile userProfile) {
    this.email = new Email(email);
    this.password = new Password(password);
    setUserProfile(userProfile);
    setRoles(roles);
  }

  public UserAuth(UUID id, String email, String password, List<Role> roles, boolean active, UserProfile userProfile) {
    this.id = id;
    this.active = active;
    this.email = new Email(email);
    this.password = new Password(password);
    setRoles(roles);
    setUserProfile(userProfile);
  }

  public void setUserProfile(UserProfile userProfile) {
    if (userProfile == null) throw new InvalidAttributeException("UserProfile cannot be empty");
    this.userProfile = userProfile;
  }

  public void setRoles(List<Role> role) {
    if (role == null || role.isEmpty()) throw new InvalidAttributeException("Role cannot be empty");
    this.roles = role;
  }

  public void addRole(String role) {
    if (role == null || role.isBlank()) throw new InvalidAttributeException("Role cannot be null or empty");

    var empty = roles.stream().filter(r -> r.equals(role)).collect(Collectors.toList()).isEmpty();

    if (empty) {
      this.roles.add(new Role(role));
    }
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email.getValue();
  }

  public void confirmEmail() {
    this.active = true;
  }

  public String getPassword() {
    return password.getValue();
  }

  public List<Role> getRoles() {
    return roles;
  }

  public boolean isActive() {
    return active;
  }

  public UserProfile getUserProfile() {
    return userProfile;
  }
}
