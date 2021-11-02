package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.core.domain.entity.Activity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class ActivityDTO {

  private UUID id;
  private String title;
  private LocalDate date;
  private LocalTime timing;
  private float distance;

  public ActivityDTO(Activity activity) {
    this.id = activity.getId();
    this.title = activity.getTitle();
    this.date = activity.getDate();
    this.timing = activity.getTiming();
    this.distance = activity.getDistance();
  }
}
