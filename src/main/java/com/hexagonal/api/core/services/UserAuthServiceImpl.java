package com.hexagonal.api.core.services;

import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.exception.EmailAlreadyRegisteredException;
import com.hexagonal.api.core.ports.inbound.UserAuthServicePort;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserAuthRepositoryPort;

import java.util.stream.Collectors;

public class UserAuthServiceImpl implements UserAuthServicePort {

  private final UserAuthRepositoryPort userAuthRepository;
  private final RoleRepositoryPort roleRepository;
  private final EmailServicePort emailService;

  public UserAuthServiceImpl(UserAuthRepositoryPort userAuthRepository, RoleRepositoryPort roleRepository, EmailServicePort emailService) {
    this.userAuthRepository = userAuthRepository;
    this.roleRepository = roleRepository;
    this.emailService = emailService;
  }

  @Override
  public UserAuth register(UserAuth userAuth) {

    userAuthRepository.findByEmail(userAuth.getEmail()).ifPresent(function -> {
      throw new EmailAlreadyRegisteredException();
    });

    var account = userAuthRepository.save(
            validateRoles(userAuth)
    );

    emailService.sendConfirmation(account);

    return account;
  }

  private UserAuth validateRoles(UserAuth userAuth) {
    var listRole = userAuth.getRoles().stream()
            .map(r -> r.getRole().toUpperCase())
            .collect(Collectors.toList());

    var roles = roleRepository.findAll().stream()
            .filter(role -> listRole.contains(role.getRole()))
            .collect(Collectors.toList());

    userAuth.setRoles(roles);
    return userAuth;
  }
}
