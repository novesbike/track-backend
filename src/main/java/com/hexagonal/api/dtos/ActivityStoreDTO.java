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

    @NotBlank
    private String description;

    @NotNull
    private LocalTime timing;

    @NotNull
    @Min(0)
    private Double distance;

    @NotEmpty
    private  List<ActivitySpeed> speed;

    @NotEmpty
    private List<ActivityElevation> elevation;

    @NotEmpty
    private List<ActivityCoordinates> coordinates;

    private UUID trainingId;
}
