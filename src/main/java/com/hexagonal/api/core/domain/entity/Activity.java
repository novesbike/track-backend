package com.hexagonal.api.core.domain.entity;

import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import com.hexagonal.api.core.domain.valueobjects.Coordinate;

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
  private LocalTime duration;
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
          LocalTime duration,
          float averageSpeed,
          float distance,
          float elevation,
          List<Coordinate> coordinates
  ) {
    setUser(user);
    setTitle(title);
    this.description = description;
    setDate(date);
    setDuration(duration);
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
          LocalTime duration,
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
    setDate(date);
    setDuration(duration);
    this.averageSpeed = averageSpeed;
    this.distance = distance;
    this.elevation = elevation;
    this.coordinates = coordinates;
    setUser(user);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // setters
  private void setTitle(String title) {
    if (title == null || title.isBlank()) throw new InvalidAttributeException("field title cannot be null or blank");
    this.title = title;
  }

  private void setDate(LocalDate date) {
    if (date == null) throw new InvalidAttributeException("date cannot be null");
    this.date = date;
  }

  private void setDuration(LocalTime duration) {
    if (duration == null) throw new InvalidAttributeException("duration cannot be null");
    this.duration = duration;
  }

  public void setUser(User user) {
    if (duration == null) throw new InvalidAttributeException("user cannot be null");
    this.user = user;
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

  public LocalTime getDuration() {
    return duration;
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
