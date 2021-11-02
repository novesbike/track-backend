package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Activity;

import java.util.List;
import java.util.UUID;

public interface GetActivityHistory {
  List<Activity> execute(UUID idUser);
}
