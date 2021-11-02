package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.entity.Coordinate;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.ports.inbound.SaveMyActivity;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.SecurityPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SaveMyActivityImpl implements SaveMyActivity {

  private final ActivityRepositoryPort repository;
  private final SecurityPort security;

  public SaveMyActivityImpl(ActivityRepositoryPort repository, SecurityPort security) {
    this.repository = repository;
    this.security = security;
  }

  @Override
  public Activity execute(
          String title,
          String description,
          LocalDate date,
          LocalTime timing,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates
  ) {

    var user = security.getAuthenticatedUser()
            .orElseThrow(() -> new BusinessRuleException("No authorized!"));

    var activity = new Activity(
            user,
            title,
            description,
            date,
            timing,
            averageSpeed,
            distance,
            elevation,
            coordinates
    );

    return repository.save(activity);
  }
}
