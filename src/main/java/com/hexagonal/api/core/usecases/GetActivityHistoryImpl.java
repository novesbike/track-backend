package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.ports.inbound.GetActivityHistory;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.SecurityPort;

import java.util.List;

public class GetActivityHistoryImpl implements GetActivityHistory {

  private final ActivityRepositoryPort repository;
  private final SecurityPort security;

  public GetActivityHistoryImpl(ActivityRepositoryPort repository, SecurityPort security) {
    this.repository = repository;
    this.security = security;
  }

  @Override
  public List<Activity> execute() {

    var user = security.getAuthenticatedUser()
            .orElseThrow(() -> new BusinessRuleException("No authorized!"));

    return repository.searchAllMyActivities(user.getId());
  }
}
