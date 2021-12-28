package com.hexagonal.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleStoreDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
