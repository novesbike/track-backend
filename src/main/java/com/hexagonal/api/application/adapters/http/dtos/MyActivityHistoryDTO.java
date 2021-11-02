package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.core.domain.valueobjects.ActivityStats;
import com.hexagonal.api.core.domain.valueobjects.MyActivityHistory;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class MyActivityHistoryDTO extends ActivityStats {

  @Getter
  private List<ActivityDTO> activities;

  public MyActivityHistoryDTO(MyActivityHistory activities) {
    super(activities.getTotalAverageSpeed(), activities.getTotalDistance());
    this.activities = activities.getActivities().stream().map(ActivityDTO::new).collect(Collectors.toList());
  }
}
