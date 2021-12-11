package com.hexagonal.api.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ActivityCoordinates {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;

    public ActivityCoordinates(Double latitude, Double longitude, LocalDateTime time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
