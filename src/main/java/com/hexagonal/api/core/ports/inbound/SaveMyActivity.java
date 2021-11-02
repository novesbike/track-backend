package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.valueobjects.Coordinate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SaveMyActivity {

  Activity execute(
          String title,
          String description,
          LocalDate date,
          LocalTime duration,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates
  );
}
