package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.model.UserAuthModel;
import com.hexagonal.api.application.adapters.persistence.model.UserProfileModel;
import com.hexagonal.api.application.adapters.persistence.jpa.UserAuthJpaRepository;
import com.hexagonal.api.application.adapters.persistence.jpa.UserProfileJpaRepository;
import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserAuthRepositoryImplTest {

  @Mock
  UserAuthJpaRepository repository;

  @Mock
  UserProfileJpaRepository profileRepository;

  @InjectMocks
  UserAuthRepositoryImpl userAuthRepository;

  @BeforeEach
  void setUp() {
    when(profileRepository.save(any(UserProfileModel.class))).thenAnswer(param -> {
      UserProfileModel entity = param.getArgument(0);
      entity.setId(UUID.randomUUID());
      return entity;
    });

    when(repository.save(any(UserAuthModel.class))).thenAnswer(param -> {
      UserAuthModel entity = param.getArgument(0);
      entity.setId(UUID.randomUUID());
      return entity;
    });
  }

  @Test
  void deveSalvarUserAuthEUserProfile() {
    var email = "email@test.com";
    var password = "pass1234";
    var userProfile = new UserProfile("foo");
    var newAccount = new UserAuth(email, "pass1234", new UserProfile("foo"));

    var saved = userAuthRepository.save(newAccount);

    Assertions.assertNotNull(saved.getId());
    Assertions.assertNotNull(saved.getUserProfile().getId());

    Assertions.assertEquals(email, saved.getEmail());
    Assertions.assertEquals(password, saved.getPassword());
    Assertions.assertEquals(userProfile.getFullName(), saved.getUserProfile().getFullName());
  }

}