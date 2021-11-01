package com.hexagonal.api.application.adapters.persistence.model;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;
  private String avatar;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<RoleModel> roles = new ArrayList<>();

  private boolean active;

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  public UserModel(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.avatar = user.getAvatar();
    this.roles = user.getRoles().stream().map(RoleModel::new).collect(Collectors.toList());
    this.active = user.isActive();
  }
}
