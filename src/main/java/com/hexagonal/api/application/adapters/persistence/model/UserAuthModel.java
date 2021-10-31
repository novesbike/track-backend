package com.hexagonal.api.application.adapters.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_auth")
public class UserAuthModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_auth_has_roles",
          joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "user_auth_id"))
  private List<RoleModel> roles = new ArrayList<>();


  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_profile_id")
  private UserProfileModel userProfile;

  @Column
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime UpdatedAt;
}

