package com.hexagonal.api.application.dtos;

import com.hexagonal.api.core.domain.entity.Coordinate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class SaveActivityDTO {

  @NotNull
  private UUID userId;
  private String title;
  private String description;
  private LocalDate date;
  private LocalTime timing;
  private float averageSpeed;
  private float distance;
  private float elevation;
  private List<Coordinate> coordinates;

}
