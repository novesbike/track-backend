package com.hexagonal.api.auth.controller;

import com.hexagonal.api.auth.request.Register;
import com.hexagonal.api.dtos.UserStoreDTO;
import com.hexagonal.api.resources.UserResource;
import com.hexagonal.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResource> register(@RequestBody @Valid Register data) {
        var user = new UserStoreDTO(
                data.getEmail(),
                data.getPassword(),
                "ROLE_CUSTOMER",
                data.getName()
        );

        return new ResponseEntity<>(new UserResource(userService.store(user)), HttpStatus.CREATED);
    }
}