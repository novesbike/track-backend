package com.hexagonal.api.application.dtos;

import com.hexagonal.api.core.domain.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDTO {

  private UUID id;
  private String email;
  private String fullName;
  private List<String> roles;

  public RegisterUserResponseDTO(UserAuth accountCreated) {
    this.id = accountCreated.getId();
    this.email = accountCreated.getEmail();
    this.fullName = accountCreated.getUserProfile().getFullName();
    this.roles = accountCreated.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
  }
}
