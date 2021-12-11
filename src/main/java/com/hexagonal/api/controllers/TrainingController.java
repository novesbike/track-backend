package com.hexagonal.api.controllers;

import com.hexagonal.api.auth.service.AuthService;
import com.hexagonal.api.dtos.TrainingStoreDTO;
import com.hexagonal.api.dtos.TrainingUpdateDTO;
import com.hexagonal.api.models.User;
import com.hexagonal.api.resources.TrainingResource;
import com.hexagonal.api.services.TrainingService;
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
@RequestMapping("v1/groups/{groupId}/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService service;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<TrainingResource>> index(@PathVariable UUID groupId) {
        var trainings = service.index(groupId);
        return ResponseEntity.ok(TrainingResource.collection(trainings));
    }

    @PreAuthorize("hasAnyRole('TRAINER')")
    @PostMapping
    public ResponseEntity<TrainingResource> store(@PathVariable UUID groupId, @RequestBody @Valid TrainingStoreDTO data) {
        User user = authService.getAuthenticatedUser();

        return new ResponseEntity<>(new TrainingResource(service.store(groupId, user.getId(), data)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingResource> show(@PathVariable UUID groupId, @PathVariable UUID id) {
        var role = service.show(id, groupId);
        return ResponseEntity.ok(new TrainingResource(role));
    }

    @PreAuthorize("hasAnyRole('TRAINER')")
    @PutMapping("/{id}")
    public ResponseEntity<TrainingResource> update(@PathVariable UUID groupId, @PathVariable UUID id, @Valid @RequestBody TrainingUpdateDTO data) {
        User user = authService.getAuthenticatedUser();

        return ResponseEntity.ok(new TrainingResource(service.update(id, groupId, user.getId(), data)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID groupId, @PathVariable UUID id) {
        User user = authService.getAuthenticatedUser();

        switch (user.getRole().getName()) {
            case "ROLE_ADMIN":
                service.destroy(id, groupId);

            case "ROLE_TRAINER":
                service.destroy(id, groupId, user.getId());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}