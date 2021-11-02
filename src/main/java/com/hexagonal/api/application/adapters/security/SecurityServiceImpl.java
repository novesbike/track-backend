package com.hexagonal.api.application.adapters.security;

import com.hexagonal.api.application.adapters.security.model.UserSecurity;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.outbound.SecurityPort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityPort {

  public static UserSecurity authenticated() {
    try {
      return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception ex) {
      return null;
    }
  }

  @Override
  public Optional<User> getAuthenticatedUser() {
    var user = authenticated();

    if (user == null) {
      return Optional.empty();
    }

    return Optional.of(user);
  }

}
