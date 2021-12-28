package com.hexagonal.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TrainingStoreDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
