package com.hexagonal.api.resources;

import com.hexagonal.api.models.Activity;
import com.hexagonal.api.models.ActivityCoordinates;
import com.hexagonal.api.models.ActivityElevation;
import com.hexagonal.api.models.ActivitySpeed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated()
public class ActivityResource {
  private UUID id;
  private String title;
  private String description;
  private LocalTime timing;
  private Double distance;
  private Double speedAverage;
  private Double speed;
  private Double elevationAverage;
  private Double altimetry;
  private List<ActivityCoordinates> coordinates;
  private String trainingId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

//    public ActivityResource(Activity activity) {
//        this.id = activity.getId();
//        this.title = activity.getTitle();
//        this.description = activity.getDescription();
//        this.timing = activity.getTiming();
//        this.distance = activity.getDistance();
//        this.speed = activity.getSpeed();
//        this.speedAverage = this.calculateSpeedAverage();
//        this.altimetry = activity.getAltimetry();
//        this.elevationAverage = this.calculateElevationAverage();
//        this.coordinates = activity.getCoordinates();
//        this.trainingId = activity.getTraining() == null ? null : activity.getTraining().getId().toString();
//        this.createdAt = activity.getCreatedAt();
//        this.updatedAt = activity.getUpdatedAt();
//    }
//
//    public static List<ActivityResource> collection(List<Activity> activities) {
//        return activities.stream().map(ActivityResource::new).collect(Collectors.toList());
//    }

//    private Double calculateSpeedAverage() {
//        var speeds = this.speed;
//
//        Double total = 0.0;
//
//        for (ActivitySpeed speed : speeds) {
//            total += speed.getValue();
//        }
//
//        return total / speeds.size();
//    }
//
//    private Double calculateElevationAverage() {
//        var elevations = this.elevation;
//
//        Double total = 0.0;
//
//        for (ActivityElevation speed : elevations) {
//            total += speed.getValue();
//        }
//
//        return total / elevations.size();
//    }
}