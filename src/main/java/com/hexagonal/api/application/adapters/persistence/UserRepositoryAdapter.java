package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.UserJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.application.adapters.persistence.model.UserModel;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserJpaRepository repository;

  @Override
  public User save(User user) {
    var model = toModel(user);
    var saved = repository.save(model);
    return toEntity(saved);
  }

  @Override
  public Optional<User> findEmail(String email) {
    var model = repository.findByEmail(email);

    if (model != null) {
      return Optional.of(toEntity(model));
    }

    return Optional.empty();
  }

  private User toEntity(UserModel saved) {

    var roles = saved.getRoles()
            .stream()
            .map(r -> new Role(r.getId(), r.getName(), r.getDescription(), r.getCreatedAt()))
            .collect(Collectors.toList());

    return new User(
            saved.getId(),
            saved.getEmail(),
            saved.getPassword(),
            saved.getName(),
            saved.getAvatar(),
            roles,
            saved.isActive(),
            saved.getCreatedAt(),
            saved.getUpdatedAt()

    );
  }

  private UserModel toModel(User user) {

    var roles = user.getRoles().stream().map(RoleModel::new).collect(Collectors.toList());

    return UserModel.builder()
            .id(user.getId())
            .email(user.getEmail())
            .password(user.getPassword())
            .name(user.getName())
            .active(user.isActive())
            .avatar(user.getAvatar())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .roles(roles)
            .build();
  }
}
