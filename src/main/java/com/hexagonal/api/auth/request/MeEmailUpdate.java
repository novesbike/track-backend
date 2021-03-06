package com.hexagonal.api.auth.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MeEmailUpdate {
    @NotBlank
    @Email
    private String email;
}