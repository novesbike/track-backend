package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Activity  {

  private UUID id;
  private String title;
  private String description;
  private LocalDate date;
  private final LocalTime timing;
  private final float averageSpeed;
  private final float distance;
  private final float elevation;
  private final List<Coordinate> coordinates;
  private User user;
  private Instant createdAt;
  private Instant updatedAt;


  // save
  public Activity(
          User user,
          String title,
          String description,
          LocalDate date,
          LocalTime timing,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates
  ) {
    this.user = user;
    setTitle(title);
    this.description = description;
    this.date = date;
    this.timing = timing;
    this.averageSpeed = averageSpeed;
    this.distance = distance;
    this.elevation = elevation;
    this.coordinates = coordinates;
  }

  public Activity(
          UUID id,
          String title,
          String description,
          LocalDate date,
          LocalTime timing,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates,
          User user,
          Instant createdAt,
          Instant updatedAt
  ) {
    this.id = id;
    setTitle(title);
    this.description = description;
    this.date = date;
    this.timing = timing;
    this.averageSpeed = averageSpeed;
    this.distance = distance;
    this.elevation = elevation;
    this.coordinates = coordinates;
    this.user = user;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // setters
  public void setTitle(String title) {
    if (title == null || title.isBlank()) throw new InvalidAttributeException("field title cannot be null or blank");
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalTime getTiming() {
    return timing;
  }

  public float getAverageSpeed() {
    return averageSpeed;
  }

  public float getDistance() {
    return distance;
  }

  public float getElevation() {
    return elevation;
  }

  public User getUser() {
    return user;
  }

  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

}
