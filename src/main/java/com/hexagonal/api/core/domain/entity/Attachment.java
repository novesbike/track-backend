package com.hexagonal.api.core.domain.entity;

import java.util.UUID;

public class Attachment {

  private UUID id;
  private String name;
  private String type;
  private byte[] data;
  private User user;

  public Attachment(String name, String type, byte[] data, User user) {
    this.name = name;
    this.type = type;
    this.data = data;
    this.user = user;
  }

  public Attachment(UUID id, String name, String type, byte[] data, User user) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.data = data;
    this.user = user;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public byte[] getData() {
    return data;
  }

  public User getUser() {
    return user;
  }

}
