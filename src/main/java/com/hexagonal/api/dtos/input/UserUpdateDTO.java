package com.hexagonal.api.dtos.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDTO {
    @NotBlank
    private String role;
}