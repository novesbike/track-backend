package com.hexagonal.api.services;

import com.hexagonal.api.dtos.input.ActivityStoreDTO;
import com.hexagonal.api.dtos.input.ActivityUpdateDTO;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Activity;
import com.hexagonal.api.models.ActivityStats;
import com.hexagonal.api.models.User;
import com.hexagonal.api.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    private final ActivityRepository repository;
    private final UserService userService;
    private final TrainingService trainingService;

    @Autowired
    public ActivityService(ActivityRepository repository, UserService userService, TrainingService trainingService) {
        this.repository = repository;
        this.userService = userService;
        this.trainingService = trainingService;
    }

    public List<Activity> index(UUID userId) {
        User user = userService.show(userId);

        return repository.findByUserId(user.getId());
    }

    public Activity store(UUID userId, ActivityStoreDTO data) {
        var user = userService.show(userId);

        var activity = new Activity(
            data.getTitle(),
            data.getDescription(),
            data.getDuration(),
            data.getDistance(),
            data.getAverageSpeed(),
            data.getAltimetry(),
            data.getCoordinates(),
            user
        );

        if (data.getTrainingId() != null) {
            activity.setTraining(trainingService.show(data.getTrainingId()));
        }

        return repository.save(activity);
    }

    public Activity show(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
    }

    public Activity update(UUID id, UUID userId, ActivityUpdateDTO data) {
        userService.show(userId);

        Activity activity = this.show(id, userId);

        activity.setTitle(data.getTitle());
        activity.setDescription(data.getDescription());

        return repository.save(activity);
    }

    public void destroy(UUID id, UUID userId) {
        this.show(id, userId);
        repository.deleteById(id);
    }

    public ActivityStats getMyStats(UUID userId) {

        if (repository.count() > 0) {
            return repository.getMyStats(userId);
        }

        return new ActivityStats();
    }
}