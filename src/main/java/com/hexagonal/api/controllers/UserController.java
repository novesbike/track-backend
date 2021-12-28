package com.hexagonal.api.controllers;

import com.hexagonal.api.dtos.input.UserStoreDTO;
import com.hexagonal.api.dtos.input.UserUpdateDTO;
import com.hexagonal.api.resources.UserResource;
import com.hexagonal.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResource>> index() {
        var users = service.index();
        return ResponseEntity.ok(UserResource.collection(users));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResource> store(@RequestBody @Valid UserStoreDTO data) {
        return new ResponseEntity<>(new UserResource(service.store(data)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> show(@PathVariable UUID id) {
        var user = service.show(id);
        return ResponseEntity.ok(new UserResource(user));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResource> update(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO data) {
        return ResponseEntity.ok(new UserResource(service.update(id, data)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        service.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/activate")
    public ResponseEntity<UserResource> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(new UserResource(service.activate(id, true)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<UserResource> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(new UserResource(service.activate(id, false)));
    }
}