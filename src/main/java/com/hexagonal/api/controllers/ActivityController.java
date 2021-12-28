package com.hexagonal.api.controllers;

import com.hexagonal.api.auth.service.AuthService;
import com.hexagonal.api.dtos.input.ActivityStoreDTO;
import com.hexagonal.api.dtos.output.ActivityDTO;
import com.hexagonal.api.dtos.output.ActivityResumeDTO;
import com.hexagonal.api.models.ActivityStats;
import com.hexagonal.api.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("v1/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService service;
    private final AuthService authService;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ActivityDTO> store(@RequestBody @Valid ActivityStoreDTO data) {
        var user = authService.getAuthenticatedUser();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ActivityDTO(service.store(user.getId(), data)));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> show(@PathVariable UUID id) {
        var user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(new ActivityDTO(service.show(id, user.getId())));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<ActivityResumeDTO>> index() {
        var user = authService.getAuthenticatedUser();
        var activityList= service.index(user.getId()).stream().map(ActivityResumeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(activityList);

    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        var user = authService.getAuthenticatedUser();
        service.destroy(id, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/stats")
    public ResponseEntity<ActivityStats> getMyStats() {
        var user = authService.getAuthenticatedUser();
        ActivityStats stats = service.getMyStats(user.getId());
        return ResponseEntity.ok(stats);
    }


}