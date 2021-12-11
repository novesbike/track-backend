package com.hexagonal.api.resources;

import com.hexagonal.api.models.User;
import com.hexagonal.api.services.StorageService;
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
public class UserResource {
    private UUID id;
    private String email;
    private boolean active;
    private LocalDateTime emailVerifiedAt;
    private String role;
    private String name;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResource(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.active = user.isActive();
        this.emailVerifiedAt = user.getEmailVerifiedAt();
        this.role = user.getRole().getName();
        this.name = user.getName();
        this.avatar = user.getAvatar() == null ? null : StorageService.get(user.getAvatar()).toString();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public static List<UserResource> collection(List<User> users) {
        return users.stream().map(UserResource::new).collect(Collectors.toList());
    }
}