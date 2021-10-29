package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.util.UUID;

public class UserProfile {

  private UUID id;
  private String fullName;
  private String avatar;

  public UserProfile(String fullName) {
    setFullName(fullName);
  }

  public UserProfile(String fullName, String avatar) {
    setFullName(fullName);
    setAvatar(avatar);
  }

  public UserProfile(UUID id, String fullName, String avatar) {
    this.id = id;
    setFullName(fullName);
    setAvatar(avatar);
  }

  public void setFullName(String fullName) {
    if (fullName == null || fullName.isBlank())
      throw new InvalidAttributeException("FullName cannot be blank");

    this.fullName = fullName;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
