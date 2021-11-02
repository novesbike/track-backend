package com.hexagonal.api.application.adapters.persistence.model;

import com.hexagonal.api.core.domain.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class RoleModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String description;

  @CreationTimestamp
  private Instant createdAt;

  public RoleModel(Role domain) {
    this.id = domain.getId();
    this.name = domain.getRole();
    this.description = domain.getDescription();
    this.createdAt = domain.getCreatedAt();
  }

  public RoleModel(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Role toDomain() {
    return new Role(
            id,
            name,
            description,
            createdAt
    );
  }
}
