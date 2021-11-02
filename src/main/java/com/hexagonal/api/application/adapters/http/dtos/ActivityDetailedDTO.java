package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.entity.Coordinate;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class ActivityDetailedDTO {

  private UUID id;
  private UUID idUser;
  private String title;
  private String description;
  private LocalDate date;
  private LocalTime timing;
  private float averageSpeed;
  private float distance;
  private float elevation;
  private Instant createdAt;
  private Instant updatedAt;
  private List<Coordinate> coordinates;


  public ActivityDetailedDTO(Activity activity) {
    this.id = activity.getId();
    this.idUser = activity.getUser().getId();
    this.title = activity.getTitle();
    this.description = activity.getDescription();
    this.date = activity.getDate();
    this.timing = activity.getTiming();
    this.averageSpeed = activity.getAverageSpeed();
    this.distance = activity.getDistance();
    this.elevation = activity.getElevation();
    this.coordinates = activity.getCoordinates();
    this.createdAt = activity.getCreatedAt();
    this.updatedAt = activity.getUpdatedAt();
  }
}
