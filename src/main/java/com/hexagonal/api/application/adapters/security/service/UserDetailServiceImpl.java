package com.hexagonal.api.application.adapters.security.service;

import com.hexagonal.api.application.adapters.persistence.UserRepositoryAdapter;
import com.hexagonal.api.application.adapters.security.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepositoryAdapter repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = repository
            .findEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email));

    return new UserSecurity(user);
  }
}
