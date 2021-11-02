package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.inbound.FetchAllUsers;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;

import java.util.List;

public class FetchAllUsersImpl implements FetchAllUsers {

  private final UserRepositoryPort repository;

  public FetchAllUsersImpl(UserRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public List<User> execute() {
    return repository.findAll();
  }

}
