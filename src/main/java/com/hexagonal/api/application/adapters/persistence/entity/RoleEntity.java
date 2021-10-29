package com.hexagonal.api.application.adapters.persistence.entity;

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
public class RoleEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String role;

  public RoleEntity(String role) {
    this.role = role;
  }
}

