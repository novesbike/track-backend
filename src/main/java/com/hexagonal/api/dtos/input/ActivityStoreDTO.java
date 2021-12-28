package com.hexagonal.api.dtos;

import com.hexagonal.api.models.ActivityCoordinates;
import com.hexagonal.api.models.ActivityElevation;
import com.hexagonal.api.models.ActivitySpeed;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class ActivityStoreDTO {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalTime duration;

    @Min(0)
    private double distance;

    @Min(0)
    private double averageSpeed;

    private double altimetry;

    @NotEmpty
    private List<ActivityCoordinates> coordinates;

    private UUID trainingId;
}
