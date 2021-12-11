package com.hexagonal.api.services;

import com.hexagonal.api.dtos.TrainingStoreDTO;
import com.hexagonal.api.dtos.TrainingUpdateDTO;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Group;
import com.hexagonal.api.models.Training;
import com.hexagonal.api.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainingService {
    private final TrainingRepository repository;
    private final GroupService groupService;

    @Autowired
    public TrainingService(TrainingRepository repository, GroupService groupService) {
        this.repository = repository;
        this.groupService = groupService;
    }

    public List<Training> index(UUID groupId) {
        return repository.findByGroupId(groupId);
    }

    public Training store(UUID groupId, UUID userId, TrainingStoreDTO data) {
        Group group = groupService.show(groupId, userId);

        var training = new Training(
                data.getTitle(),
                data.getDescription(),
                group
        );

        return repository.save(training);
    }

    public Training show(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Training not found"));
    }

    public Training show(UUID id, UUID groupId) {
        return repository.findByIdAndGroupId(id, groupId).orElseThrow(() -> new ResourceNotFoundException("Training not found"));
    }

    public Training update(UUID id, UUID groupId, UUID userId, TrainingUpdateDTO data) {
        groupService.show(groupId, userId);

        Training training = this.show(id, groupId);

        training.setTitle(data.getTitle());
        training.setDescription(data.getDescription());

        return repository.save(training);
    }

    public void destroy(UUID id, UUID groupId) {
        this.show(id, groupId);

        repository.deleteById(id);
    }

    public void destroy(UUID id, UUID groupId, UUID userId) {
        groupService.show(groupId, userId);

        this.show(id, groupId);

        repository.deleteById(id);
    }
}