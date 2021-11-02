package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.inbound.FetchAllUsers;
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

  private final FetchAllUsers fetchAllUsers;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<User>> index() {
    return ResponseEntity.ok(fetchAllUsers.execute());
  }
}
