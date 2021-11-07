package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.core.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserUpdatedDTO {

  private UUID id;
  private String name;
  private String avatar;

  public UserUpdatedDTO(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.avatar = user.getAvatar();
  }
}
