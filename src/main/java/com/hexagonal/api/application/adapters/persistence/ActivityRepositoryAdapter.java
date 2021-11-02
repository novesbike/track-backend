package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.ActivityJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.ActivityModel;
import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.ports.outbound.ActivityRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryAdapter implements ActivityRepositoryPort {

  private final ActivityJpaRepository repository;

  @Override
  public Activity save(Activity activity) {
    var activityModel = new ActivityModel(activity);

    var saved = repository.save(activityModel);
    return saved.toDomain();
  }

  @Override
  public List<Activity> searchAllMyActivities(UUID id) {
    var result = repository.findByUserId(id);
    return result.stream().map(ActivityModel::toDomain).collect(Collectors.toList());
  }

  public List<Activity> findAll() {
    var result = repository.findAll();
    return result.stream().map(ActivityModel::toDomain).collect(Collectors.toList());
  }
}
