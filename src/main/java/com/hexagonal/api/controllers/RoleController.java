package com.hexagonal.api.controllers;

import com.hexagonal.api.dtos.RoleStoreDTO;
import com.hexagonal.api.dtos.RoleUpdateDTO;
import com.hexagonal.api.models.Role;
import com.hexagonal.api.resources.RoleResource;
import com.hexagonal.api.services.RoleService;
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
@RequestMapping("v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoleResource>> index() {
        var roles = service.index();
        return ResponseEntity.ok(RoleResource.collection(roles));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RoleResource> store(@RequestBody @Valid RoleStoreDTO data) {
        var role = new Role(
                data.getName(),
                data.getDescription().toUpperCase()
        );

        return new ResponseEntity<>(new RoleResource(service.store(role)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleResource> show(@PathVariable UUID id) {
        var role = service.show(id);
        return ResponseEntity.ok(new RoleResource(role));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleResource> update(@PathVariable UUID id, @Valid @RequestBody RoleUpdateDTO data) {
        var role = new Role(
                data.getName(),
                data.getDescription().toUpperCase()
        );

        return ResponseEntity.ok(new RoleResource(service.update(id, role)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable UUID id) {
        service.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}