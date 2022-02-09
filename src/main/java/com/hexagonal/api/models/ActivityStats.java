package com.hexagonal.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityStats {
  private double totalAverageSpeed;
  private double totalDistance;

  public ActivityStats(double totalAverageSpeed, double totalDistance) {
    this.totalAverageSpeed = totalAverageSpeed;
    this.totalDistance = totalDistance;
  }
}