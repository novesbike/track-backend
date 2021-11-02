package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.valueobjects.ActivityStats;

import java.util.List;
import java.util.UUID;

public interface ActivityRepositoryPort {
  Activity save(Activity activity);
  List<Activity> searchAllMyActivities(UUID id);
  ActivityStats getMyStats(UUID userId);
}
