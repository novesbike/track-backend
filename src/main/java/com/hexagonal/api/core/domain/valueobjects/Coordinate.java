package com.hexagonal.api.core.domain.valueobjects;

import java.util.Objects;

public class Coordinate {

  private final Double latitude;
  private final Double longitude;

  public Coordinate(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinate that = (Coordinate) o;
    return latitude.equals(that.latitude) && longitude.equals(that.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }
}
