package com.hexagonal.api.dtos.output;

import com.hexagonal.api.models.Activity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter @Setter
public class ActivityResumeDTO {
  private UUID id;
  private String title;
  private LocalTime duration;
  private Double distance;
  private Double averageSpeed;
  private Double altimetry;
  private LocalDateTime createdAt;

  public ActivityResumeDTO(Activity activity) {
    this.id = activity.getId();
    this.title = activity.getTitle();
    this.duration = activity.getDuration();
    this.distance = activity.getDistance();
    this.averageSpeed = activity.getAverageSpeed();
    this.altimetry = activity.getAltimetry();
    this.createdAt = activity.getCreatedAt();
  }
}
