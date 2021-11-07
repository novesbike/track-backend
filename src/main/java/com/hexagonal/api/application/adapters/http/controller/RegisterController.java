package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.http.dtos.RegisterUserRequestDTO;
import com.hexagonal.api.application.adapters.http.dtos.RegisterUserResponseDTO;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.inbound.CreateAccountUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class RegisterController {

  private final CreateAccountUser createAccountUser;
  private final BCryptPasswordEncoder encoder;

  @PostMapping("/register")
  public ResponseEntity<RegisterUserResponseDTO> createAccount(@RequestBody RegisterUserRequestDTO register) {

    var account = createAccountUser.execute(
            register.getName(),
            register.getEmail(),
            encoder.encode(register.getPassword()),
            register.getAvatar()
    );

    return new ResponseEntity<>(new RegisterUserResponseDTO(account), HttpStatus.CREATED);
  }

}
