package com.hexagonal.api.application.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hexagonal.api.core.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterUserResponseDTO {

  private UUID id;
  private String email;
  private String fullName;
  private List<String> roles;

  public RegisterUserResponseDTO(User accountCreated) {
    this.id = accountCreated.getId();
    this.email = accountCreated.getEmail();
    this.fullName = accountCreated.getName();
    this.roles = accountCreated.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
  }
}
