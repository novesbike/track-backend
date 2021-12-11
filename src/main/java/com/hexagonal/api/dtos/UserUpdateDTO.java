package com.hexagonal.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDTO {
    @NotBlank
    private String role;
}