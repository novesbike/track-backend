package com.hexagonal.api.application.adapters.web.controller;

import com.hexagonal.api.application.dtos.RegisterUserRequestDTO;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.domain.entity.UserProfile;
import com.hexagonal.api.core.ports.inbound.UserAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserAuthServicePort userService;

  @PostMapping
  private ResponseEntity<Void> register(@RequestBody RegisterUserRequestDTO user) {

    userService.register(
            new UserAuth(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map(Role::new).collect(Collectors.toList()),
                    new UserProfile(user.getFullName())
            )
    );

    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}
