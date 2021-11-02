package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Activity;

import java.util.List;

public interface GetActivityHistory {
  List<Activity> execute();
}
