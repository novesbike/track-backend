package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.persistence.UserRepositoryAdapter;
import com.hexagonal.api.core.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

  private final UserRepositoryAdapter adapter;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<User>> index() {
    var list = adapter.findAll();
    return ResponseEntity.ok(list);
  }
}
