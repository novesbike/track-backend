package com.hexagonal.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Getter
@Setter
@EqualsAndHashCode
public class ActivityCoordinates {

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.NUMBER)
  private Timestamp timestamp;

  public ActivityCoordinates(Double latitude, Double longitude, Timestamp timestamp) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.timestamp = timestamp;
  }

}
