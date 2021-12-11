package com.hexagonal.api.controllers;

import com.hexagonal.api.auth.service.AuthService;
import com.hexagonal.api.dtos.GroupStoreDTO;
import com.hexagonal.api.dtos.GroupUpdateDTO;
import com.hexagonal.api.models.Group;
import com.hexagonal.api.models.User;
import com.hexagonal.api.resources.GroupResource;
import com.hexagonal.api.services.GroupService;
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
@RequestMapping("v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<GroupResource>> index() {
        User user = authService.getAuthenticatedUser();

        if (user.getRole().getName().equals("ROLE_TRAINER")) {
            return ResponseEntity.ok(GroupResource.collection(service.index(user.getId())));
        }

        return ResponseEntity.ok(GroupResource.collection(service.index()));
    }

    @PreAuthorize("hasAnyRole('TRAINER')")
    @PostMapping
    public ResponseEntity<GroupResource> store(@RequestBody @Valid GroupStoreDTO data) {
        User user = authService.getAuthenticatedUser();

        return new ResponseEntity<>(new GroupResource(service.store(user.getId(), data)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResource> show(@PathVariable UUID id) {
        User user = authService.getAuthenticatedUser();

        Group group;

        if (user.getRole().getName().equals("ROLE_TRAINER")) {
            group = service.show(id, user.getId());
        } else {
            group = service.show(id);
        }

        return ResponseEntity.ok(new GroupResource(group));
    }

    @PreAuthorize("hasAnyRole('TRAINER')")
    @PutMapping("/{id}")
    public ResponseEntity<GroupResource> update(@PathVariable UUID id, @Valid @RequestBody GroupUpdateDTO data) {
        User user = authService.getAuthenticatedUser();

        return ResponseEntity.ok(new GroupResource(service.update(id, user.getId(), data)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        User user = authService.getAuthenticatedUser();

        switch (user.getRole().getName()) {
            case "ROLE_ADMIN":
                service.destroy(id);

            case "ROLE_TRAINER":
                service.destroy(id, user.getId());
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}