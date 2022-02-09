package com.hexagonal.api.dtos.input;

import com.hexagonal.api.models.ActivityCoordinates;
import com.hexagonal.api.models.ActivityElevation;
import com.hexagonal.api.models.ActivitySpeed;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Data
public class ActivityUpdateDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalTime timing;

    @NotNull
    @Min(0)
    private Double distance;

    @NotNull
    private List<ActivitySpeed> speed;

    @NotNull
    private List<ActivityElevation> elevation;

    @NotNull
    private List<ActivityCoordinates> coordinates;
}
