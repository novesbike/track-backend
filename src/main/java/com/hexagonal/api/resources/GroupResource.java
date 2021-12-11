package com.hexagonal.api.resources;

import com.hexagonal.api.enums.GroupLevel;
import com.hexagonal.api.models.Group;
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
public class GroupResource {
    private UUID id;
    private String title;
    private String description;
    private GroupLevel level;
    private String trainer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GroupResource(Group group) {
        this.id = group.getId();
        this.title = group.getTitle();
        this.description = group.getDescription();
        this.level = group.getLevel();
        this.trainer = group.getTrainer().getName();
        this.createdAt = group.getCreatedAt();
        this.updatedAt = group.getUpdatedAt();
    }

    public static List<GroupResource> collection(List<Group> groups) {
        return groups.stream().map(GroupResource::new).collect(Collectors.toList());
    }
}