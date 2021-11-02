package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.User;

import java.util.List;

public interface FetchAllUsers {
  List<User> execute();
}
