package com.hexagonal.api.application.configs;

import com.hexagonal.api.NovesBikeApplication;
import com.hexagonal.api.core.ports.inbound.*;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;
import com.hexagonal.api.core.usecases.*;
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

  @Bean
  GetActivityHistory getActivityHistory(ActivityRepositoryPort repository) {
    return new GetActivityHistoryImpl(repository);
  }

  @Bean
  SaveMyActivity saveMyActivity(ActivityRepositoryPort repository, UserRepositoryPort userRepository) {
    return new SaveMyActivityImpl(repository, userRepository);
  }

}
