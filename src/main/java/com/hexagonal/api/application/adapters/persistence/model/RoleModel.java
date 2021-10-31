package com.hexagonal.api.application.adapters.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleModel {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String role;

  public RoleModel(String role) {
    this.role = role;
  }
}

