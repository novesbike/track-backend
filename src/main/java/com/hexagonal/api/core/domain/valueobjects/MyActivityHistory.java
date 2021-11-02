package com.hexagonal.api.core.domain.valueobjects;

import com.hexagonal.api.core.domain.entity.Activity;

import java.util.ArrayList;
import java.util.List;

public class MyActivityHistory extends ActivityStats {

  private final List<Activity> activities = new ArrayList<>();

  public MyActivityHistory(double totalAverageSpeed, double totalDistance, List<Activity> activities) {
    super(totalAverageSpeed, totalDistance);
    this.activities.addAll(activities);
  }

  public List<Activity> getActivities() {
    return activities;
  }
}
