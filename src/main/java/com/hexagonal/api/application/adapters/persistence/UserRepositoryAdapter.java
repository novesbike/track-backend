package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.UserJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.application.adapters.persistence.model.UserModel;
import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserJpaRepository repository;

  @Override
  public User save(User user) {
    var model = toModel(user);
    return repository.save(model).toDomain();
  }

  @Override
  public Optional<User> findEmail(String email) {
    var user = repository.findByEmail(email);

    if (user != null) {
      return Optional.of(user.toDomain());
    }

    return Optional.empty();
  }

  @Override
  public Optional<User> findUserById(UUID idUser) {
    var user = repository.findById(idUser);

    if(user.isPresent()) {
      return Optional.of(user.get().toDomain());
    }

    return Optional.empty();
  }

  @Override
  public List<User> findAll() {
    var list = repository.findAll();
    return list.stream().map(UserModel::toDomain).collect(Collectors.toList());
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
