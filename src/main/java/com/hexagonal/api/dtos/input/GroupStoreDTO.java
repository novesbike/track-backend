package com.hexagonal.api.dtos.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GroupStoreDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String level;
}
