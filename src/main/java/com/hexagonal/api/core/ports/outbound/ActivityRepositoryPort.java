package com.hexagonal.api.core.ports.outbound;

import com.hexagonal.api.core.domain.entity.Activity;

import java.util.List;
import java.util.UUID;

public interface ActivityRepositoryPort {
  Activity save(Activity activity);
  List<Activity> searchAllMyActivities(UUID id);
}
