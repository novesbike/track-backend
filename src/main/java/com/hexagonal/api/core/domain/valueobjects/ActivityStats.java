package com.hexagonal.api.core.domain.valueobjects;

import lombok.Data;

@Data
public class ActivityStats {
  private double totalAverageSpeed;
  private double totalDistance;

  public ActivityStats(double totalAverageSpeed, double totalDistance) {
    this.totalAverageSpeed = totalAverageSpeed;
    this.totalDistance = totalDistance;
  }
}
