package com.hexagonal.api.resources;

import com.hexagonal.api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RoleResource(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.createdAt = role.getCreatedAt();
        this.updatedAt = role.getUpdatedAt();
    }

    public static List<RoleResource> collection(List<Role> roles) {
        return roles.stream().map(RoleResource::new).collect(Collectors.toList());
    }
}