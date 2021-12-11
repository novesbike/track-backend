package com.hexagonal.api.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ActivityElevation {
    @NotNull
    private  Double value;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private  LocalDateTime time;

    public ActivityElevation(Double value, LocalDateTime time) {
        this.value = value;
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
