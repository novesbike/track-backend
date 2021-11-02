package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.exception.NotAuthorizedException;
import com.hexagonal.api.core.domain.valueobjects.ActivityStats;
import com.hexagonal.api.core.ports.inbound.GetMyStats;
import com.hexagonal.api.core.ports.outbound.SecurityPort;
import com.hexagonal.api.core.ports.outbound.repository.ActivityRepositoryPort;

public class GetMyStatsImpl implements GetMyStats {

  private final ActivityRepositoryPort repository;
  private final SecurityPort security;

  public GetMyStatsImpl(ActivityRepositoryPort repository, SecurityPort security) {
    this.repository = repository;
    this.security = security;
  }

  @Override
  public ActivityStats execute() {
    var user = security.getAuthenticatedUser().orElseThrow(NotAuthorizedException::new);
    return repository.getMyStats(user.getId());
  }
}
