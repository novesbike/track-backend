package com.hexagonal.api.models;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "activities")
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalTime duration;

    @Column(nullable = false)
    private double distance;

    @Column(nullable = false)
    private double averageSpeed;

    @Column(nullable = false)
    private double altimetry;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<ActivityCoordinates> coordinates = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private Training training;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Activity(String title, String description, LocalTime duration, double distance, double averageSpeed, double altimetry, List<ActivityCoordinates> coordinates, User user, Training training) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.altimetry = altimetry;
        this.coordinates = coordinates;
        this.user = user;
        this.training = training;
    }

    public Activity(String title, String description, LocalTime duration, double distance, double averageSpeed, double altimetry, List<ActivityCoordinates> coordinates, User user) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.altimetry = altimetry;
        this.coordinates = coordinates;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getAltimetry() {
        return altimetry;
    }

    public void setAltimetry(double altimetry) {
        this.altimetry = altimetry;
    }

    public List<ActivityCoordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<ActivityCoordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
