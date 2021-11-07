package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.domain.exception.EmailAlreadyRegisteredException;
import com.hexagonal.api.core.ports.inbound.CreateAccountUser;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;

public class CreateAccountUserImpl implements CreateAccountUser {

  private final UserRepositoryPort userAuthRepository;
  private final RoleRepositoryPort roleRepository;
  private final EmailServicePort emailService;

  public CreateAccountUserImpl(UserRepositoryPort userAuthRepository, RoleRepositoryPort roleRepository, EmailServicePort emailService) {
    this.userAuthRepository = userAuthRepository;
    this.roleRepository = roleRepository;
    this.emailService = emailService;
  }

  @Override
  public User execute(String name, String email, String password, String avatar) {

    userAuthRepository.findEmail(email).ifPresent(function -> {
      throw new EmailAlreadyRegisteredException();
    });

    var role = getRoleUser();

    var user = new User(
            name,
            email,
            password,
            avatar,
            role
    );

    var account = userAuthRepository.save(user);

    emailService.sendConfirmation(
            account.getName(),
            account.getEmail()
    );

    return account;
  }

  private Role getRoleUser() {
    return roleRepository.findName("ROLE_USER").orElseThrow(
            () -> new BusinessRuleException("Role default not exists")
    );
  }

}
