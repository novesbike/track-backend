package com.hexagonal.api.application.adapters.security.model;

import com.hexagonal.api.core.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserSecurity extends User implements UserDetails {

  public UserSecurity(User user) {
    super(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getName(),
            user.getAvatar(),
            user.getRoles(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return super.getRoles().stream().map(RoleSecurity::new).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return !super.isActive();
  }
}