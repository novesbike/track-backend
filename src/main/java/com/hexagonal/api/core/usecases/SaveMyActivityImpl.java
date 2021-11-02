package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.entity.Coordinate;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import com.hexagonal.api.core.domain.exception.ResourceNotFoundException;
import com.hexagonal.api.core.ports.inbound.SaveMyActivity;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class SaveMyActivityImpl implements SaveMyActivity {

  private final ActivityRepositoryPort repository;
  private final UserRepositoryPort userRepository;

  public SaveMyActivityImpl(ActivityRepositoryPort repository, UserRepositoryPort userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  @Override
  public Activity execute(
          UUID idUser,
          String title,
          String description,
          LocalDate date,
          LocalTime timing,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates
  ) {

    if (idUser == null) throw new InvalidAttributeException("The given idUser must not be null");

    var user = userRepository.findUserById(idUser).orElseThrow(
            () -> new ResourceNotFoundException("User not found")
    );

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