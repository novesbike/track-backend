package com.hexagonal.api.dtos.output;

import com.hexagonal.api.models.Activity;
import com.hexagonal.api.models.ActivityCoordinates;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class ActivityDTO {
  private UUID id;
  private String title;
  private String description;
  private LocalTime duration;
  private Double distance;
  private Double averageSpeed;
  private Double altimetry;
  private UUID trainingId;
  private List<ActivityCoordinates> coordinates;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ActivityDTO(Activity activity) {
    this.id = activity.getId();
    this.title = activity.getTitle();
    this.description = activity.getDescription();
    this.duration = activity.getDuration();
    this.distance = activity.getDistance();
    this.averageSpeed = activity.getAverageSpeed();
    this.altimetry = activity.getAltimetry();
    this.trainingId = activity.getTraining() != null ? activity.getTraining().getId() : null;
    this.coordinates = activity.getCoordinates();
    this.createdAt = activity.getCreatedAt();
    this.updatedAt = activity.getUpdatedAt();
  }
}
