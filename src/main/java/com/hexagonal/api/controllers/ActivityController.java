package com.hexagonal.api.controllers;

import com.hexagonal.api.auth.service.AuthService;
import com.hexagonal.api.dtos.ActivityStoreDTO;
import com.hexagonal.api.dtos.ActivityUpdateDTO;
import com.hexagonal.api.models.User;
import com.hexagonal.api.resources.ActivityResource;
import com.hexagonal.api.services.ActivityService;
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
@RequestMapping("v1/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService service;
    private final AuthService authService;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<ActivityResource>> index() {
        User user = authService.getAuthenticatedUser();

        return ResponseEntity.ok(ActivityResource.collection(service.index(user.getId())));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ActivityResource> store(@RequestBody @Valid ActivityStoreDTO data) {
        User user = authService.getAuthenticatedUser();

        return new ResponseEntity<>(new ActivityResource(service.store(user.getId(), data)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityResource> show(@PathVariable UUID id) {
        User user = authService.getAuthenticatedUser();

        return ResponseEntity.ok(new ActivityResource(service.show(id, user.getId())));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<ActivityResource> update(@PathVariable UUID id, @Valid @RequestBody ActivityUpdateDTO data) {
        User user = authService.getAuthenticatedUser();

        return ResponseEntity.ok(new ActivityResource(service.update(id, user.getId(), data)));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        User user = authService.getAuthenticatedUser();

        service.destroy(id, user.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}