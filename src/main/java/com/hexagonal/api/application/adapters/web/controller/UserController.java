package com.hexagonal.api.application.adapters.web.controller;

import com.hexagonal.api.application.adapters.persistence.UserRepositoryAdapter;
import com.hexagonal.api.application.dtos.RegisterUserRequestDTO;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.inbound.CreateAccountUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

  private final CreateAccountUser createAccountUser;
  private final UserRepositoryAdapter adapter;


  @PostMapping
  public ResponseEntity<User> createAccount(@RequestBody RegisterUserRequestDTO register) {

    var account = createAccountUser.execute(
            register.getName(),
            register.getEmail(),
            register.getPassword(),
            register.getAvatar()
    );

    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }


  // TODO: remover
  @GetMapping
  public ResponseEntity<List<User>> index() {
    var list = adapter.findAll();
    return ResponseEntity.ok(list);
  }

}
