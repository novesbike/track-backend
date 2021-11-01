package com.hexagonal.api.application.configs;

import com.hexagonal.api.NovesBikeApplication;
import com.hexagonal.api.core.ports.inbound.CreateAccountUser;
import com.hexagonal.api.core.ports.inbound.CreateRole;
import com.hexagonal.api.core.ports.inbound.FetchAllRoles;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;
import com.hexagonal.api.core.usecases.CreateAccountUserImpl;
import com.hexagonal.api.core.usecases.CreateRoleImpl;
import com.hexagonal.api.core.usecases.FetchAllRolesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NovesBikeApplication.class)
public class CoreInject {


  @Bean
  CreateRole createRole(RoleRepositoryPort repository) {
    return new CreateRoleImpl(repository);
  }

  @Bean
  FetchAllRoles fetchAllRoles(RoleRepositoryPort repository) {
    return new FetchAllRolesImpl(repository);
  }

  @Bean
  CreateAccountUser createAccountUser(
          UserRepositoryPort userAuthRepository,
          RoleRepositoryPort roleRepository,
          EmailServicePort emailService
  ) {
    return new CreateAccountUserImpl(userAuthRepository, roleRepository, emailService);
  }

}
