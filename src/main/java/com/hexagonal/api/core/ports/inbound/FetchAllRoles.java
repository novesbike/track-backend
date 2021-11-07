package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Role;

import java.util.List;

public interface FetchAllRoles {
  List<Role> execute();
}
