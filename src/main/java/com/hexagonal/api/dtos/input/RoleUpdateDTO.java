package com.hexagonal.api.dtos.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
