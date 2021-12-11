package com.hexagonal.api.resources;

import com.hexagonal.api.models.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingResource {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrainingResource(Training training) {
        this.id = training.getId();
        this.title = training.getTitle();
        this.description = training.getDescription();
        this.createdAt = training.getCreatedAt();
        this.updatedAt = training.getUpdatedAt();
    }

    public static List<TrainingResource> collection(List<Training> trainings) {
        return trainings.stream().map(TrainingResource::new).collect(Collectors.toList());
    }
}