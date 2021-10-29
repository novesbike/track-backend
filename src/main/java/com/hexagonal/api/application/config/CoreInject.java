package com.hexagonal.api.application.config;

import com.hexagonal.api.NovesBikeApplication;
import com.hexagonal.api.core.ports.inbound.UserAuthServicePort;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import com.hexagonal.api.core.ports.outbound.repository.RoleRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserAuthRepositoryPort;
import com.hexagonal.api.core.services.UserAuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NovesBikeApplication.class)
public class CoreInject {

  @Bean
  UserAuthServicePort userAuthService(
          UserAuthRepositoryPort userAuthRepository,
          RoleRepositoryPort roleRepository,
          EmailServicePort emailService
  ) {
    return new UserAuthServiceImpl(userAuthRepository, roleRepository, emailService);
  }

}
