package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.exception.NotAuthorizedException;
import com.hexagonal.api.core.domain.valueobjects.MyActivityHistory;
import com.hexagonal.api.core.ports.inbound.GetActivityHistory;
import com.hexagonal.api.core.ports.outbound.repository.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.SecurityPort;

public class GetActivityHistoryImpl implements GetActivityHistory {

  private final ActivityRepositoryPort repository;
  private final SecurityPort security;

  public GetActivityHistoryImpl(ActivityRepositoryPort repository, SecurityPort security) {
    this.repository = repository;
    this.security = security;
  }

  @Override
  public MyActivityHistory execute() {

    var user = security.getAuthenticatedUser().orElseThrow(NotAuthorizedException::new);

    var activities = repository.searchAllMyActivities(user.getId());
    var stats = repository.getMyStats(user.getId());

    return new MyActivityHistory(stats.getTotalAverageSpeed(), stats.getTotalDistance(), activities);
  }
}
