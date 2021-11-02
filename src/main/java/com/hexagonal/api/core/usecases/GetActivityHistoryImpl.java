package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.ports.inbound.GetActivityHistory;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetActivityHistoryImpl implements GetActivityHistory {

  private final ActivityRepositoryPort repository;

  public GetActivityHistoryImpl(ActivityRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public List<Activity> execute(UUID idUser) {
        return repository.searchAllMyActivities(idUser);
  }
}
