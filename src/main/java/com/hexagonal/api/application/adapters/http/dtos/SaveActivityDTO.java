package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.core.domain.valueobjects.Coordinate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class SaveActivityDTO {

  private String title;
  private String description;
  private LocalDate date;
  private LocalTime timing;
  private float averageSpeed;
  private float distance;
  private float elevation;
  private List<Coordinate> coordinates;

}
