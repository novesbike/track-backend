package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.RoleJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

  private final RoleJpaRepository repository;

  @Override
  public Role save(Role role) {
    var saved = repository.save(new RoleModel(role));
    return new Role(saved.getId(), saved.getName(), saved.getDescription(), saved.getCreatedAt());
  }

  @Override
  public List<Role> findAll() {
    return repository.findAll()
            .stream()
            .map(r -> new Role(r.getId(), r.getName(), r.getDescription(), r.getCreatedAt()))
            .collect(Collectors.toList());
  }

  @Override
  public Optional<Role> findName(String name) {
    var role = repository.findByName(name);

    if (role.isPresent()) {
      return Optional.of(
              new Role(
                      role.get().getId(),
                      role.get().getName(),
                      role.get().getDescription(),
                      role.get().getCreatedAt()
              )
      );
    }

    return Optional.empty();
  }
}
