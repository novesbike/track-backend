package com.hexagonal.api.application.configs;

import com.hexagonal.api.NovesBikeApplication;
import com.hexagonal.api.core.ports.inbound.*;
import com.hexagonal.api.core.ports.outbound.repository.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.SecurityPort;
import com.hexagonal.api.core.ports.outbound.repository.AttachmentRepositoryPort;
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
  FetchAllUsers fetchAllUsers(UserRepositoryPort repository) {
    return new FetchAllUsersImpl(repository);
  }

  @Bean
  GetActivityHistory getActivityHistory(ActivityRepositoryPort repository, SecurityPort security) {
    return new GetActivityHistoryImpl(repository, security);
  }

  @Bean
  SaveMyActivity saveMyActivity(ActivityRepositoryPort repository, SecurityPort security) {
    return new SaveMyActivityImpl(repository, security);
  }

  @Bean
  GetMyStats getMyStats(ActivityRepositoryPort repository, SecurityPort security) {
    return new GetMyStatsImpl(repository, security);
  }

  @Bean
  UpdateProfile updateProfile(AttachmentRepositoryPort attachmentRepositoryPort, UserRepositoryPort userJpaRepository) {
    return new UpdateProfileImpl(attachmentRepositoryPort, userJpaRepository);
  }

  @Bean
  GetUserAvatar getUserAvatar(AttachmentRepositoryPort attachmentRepositoryPort, SecurityPort securityPort) {
    return new GetUserAvatarImpl(attachmentRepositoryPort, securityPort);
  }

}
