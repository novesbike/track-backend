package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.application.adapters.persistence.model.UserAuthModel;
import com.hexagonal.api.application.adapters.persistence.model.UserProfileModel;
import com.hexagonal.api.application.adapters.persistence.jpa.UserAuthJpaRepository;
import com.hexagonal.api.application.adapters.persistence.jpa.UserProfileJpaRepository;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.ports.outbound.repository.UserAuthRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepositoryPort {

  private final UserAuthJpaRepository repository;
  private final UserProfileJpaRepository profileRepository;

  @Override
  public UserAuth save(UserAuth userAuth) {
    var entity = toEntity(userAuth);

    var profile = profileRepository.save(entity.getUserProfile());
    entity.setUserProfile(profile);

    var saved = repository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<UserAuth> findByEmail(String email) {
    var entity = repository.findByEmail(email);

    if (entity.isPresent()) {
      return Optional.of(
              new UserAuth(
                entity.get().getId(),
                entity.get().getEmail(),
                entity.get().getPassword(),
                toDomain(entity.get().getRoles()),
                entity.get().isActive(),
                toDomain(entity.get().getUserProfile()
              )
      ));
    }

    return Optional.empty();
  }

  private List<RoleModel> toEntity(List<Role> roles) {
    return roles.stream().map(r -> new RoleModel(r.getId(), r.getRole())).collect(Collectors.toList());
  }

  private UserProfileModel toEntity(UserProfile userProfile) {
    return new UserProfileModel(userProfile.getId(), userProfile.getFullName(), userProfile.getAvatar());
  }


  UserAuthModel toEntity(UserAuth userAuth) {
    var rolesEntity = toEntity(userAuth.getRoles());
    var userProfileEntity = toEntity(userAuth.getUserProfile());

    return UserAuthModel.builder()
            .id(userAuth.getId())
            .email(userAuth.getEmail())
            .password(userAuth.getPassword())
            .roles(rolesEntity)
            .userProfile(userProfileEntity)
            .build();
  }

  private UserAuth toDomain(UserAuthModel userAuthModel) {
    var rolesDomain = toDomain(userAuthModel.getRoles());
    var userProfileDomain = toDomain(userAuthModel.getUserProfile());

    return new UserAuth(
            userAuthModel.getId(),
            userAuthModel.getEmail(),
            userAuthModel.getPassword(),
            rolesDomain,
            userAuthModel.isActive(),
            userProfileDomain
    );
  }

  private UserProfile toDomain(UserProfileModel userProfile) {
    return new UserProfile(userProfile.getId(), userProfile.getFullName(), userProfile.getAvatar());
  }

  private List<Role> toDomain(List<RoleModel> roles) {
    return roles.stream().map(r -> new Role(r.getRole())).collect(Collectors.toList());
  }
}
