package com.hexagonal.api.services;

import com.hexagonal.api.dtos.input.GroupStoreDTO;
import com.hexagonal.api.dtos.input.GroupUpdateDTO;
import com.hexagonal.api.enums.GroupLevel;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Group;
import com.hexagonal.api.models.User;
import com.hexagonal.api.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    private final GroupRepository repository;
    private final UserService userService;

    @Autowired
    public GroupService(GroupRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public List<Group> index(UUID userId) {
        User user = userService.show(userId);

        return repository.findByTrainerId(user.getId());
    }

    public List<Group> index() {
        return repository.findAll();
    }

    public Group store(UUID trainerId, GroupStoreDTO data) {
        User user = userService.show(trainerId);

        var group = new Group(
                data.getTitle(),
                data.getDescription(),
                GroupLevel.cast(data.getLevel()),
                user
        );

        return repository.save(group);
    }

    public Group show(UUID id, UUID trainerId) {
        return repository.findByIdAndTrainerId(id, trainerId).orElseThrow(() -> new ResourceNotFoundException("Group not found"));
    }


    public Group show(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group not found"));
    }

    public Group update(UUID id, UUID trainerId, GroupUpdateDTO data) {
        Group group = this.show(id, trainerId);

        group.setTitle(data.getTitle());
        group.setDescription(data.getDescription());

        return repository.save(group);
    }

    public void destroy(UUID id, UUID trainerId) {
        this.show(id, trainerId);

        repository.deleteById(id);
    }


    public void destroy(UUID id) {
        this.show(id);

        repository.deleteById(id);
    }
}