package com.hexagonal.api.application.adapters.persistence.model;

import com.hexagonal.api.core.domain.entity.Activity;
import com.hexagonal.api.core.domain.entity.Coordinate;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "activities")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ActivityModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(nullable = false)
  private String title;

  private String description;
  private LocalDate date;
  private LocalTime timing;
  private float averageSpeed;
  private float distance;
  private float elevation;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @Type(type = "json")
  @Column(columnDefinition = "json")
  private List<Coordinate> coordinates;

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  public ActivityModel(Activity activity) {
    this.id = activity.getId();
    this.title = activity.getTitle();
    this.description = activity.getDescription();
    this.date = activity.getDate();
    this.timing = activity.getTiming();
    this.averageSpeed = activity.getAverageSpeed();
    this.distance = activity.getDistance();
    this.elevation = activity.getElevation();
    this.coordinates = activity.getCoordinates();
    this.user = new UserModel(activity.getUser());
    this.createdAt = activity.getCreatedAt();
    this.updatedAt = activity.getUpdatedAt();
  }

  public ActivityModel(UUID userId, String title, String description, LocalDate date, LocalTime timing, float averageSpeed, float distance, float elevation) {
    this.title = title;
    this.description = description;
    this.date = date;
    this.timing = timing;
    this.averageSpeed = averageSpeed;
    this.distance = distance;
    this.elevation = elevation;
    this.user = UserModel.builder().id(userId).build();
  }

  public Activity toDomain() {
    return new Activity(
            id,
            title,
            description,
            date,
            timing,
            averageSpeed,
            distance,
            elevation,
            coordinates,
            user.toDomain(),
            createdAt,
            updatedAt
    );
  }
}
