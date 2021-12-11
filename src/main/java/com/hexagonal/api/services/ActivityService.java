package com.hexagonal.api.services;

import com.hexagonal.api.dtos.ActivityStoreDTO;
import com.hexagonal.api.dtos.ActivityUpdateDTO;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Activity;
import com.hexagonal.api.models.Training;
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
        User user = userService.show(userId);
        Activity activity;

        if (data.getTrainingId() != null) {
            Training training = trainingService.show(data.getTrainingId());

            activity = new Activity(
                    data.getTitle(),
                    data.getDescription(),
                    data.getTiming(),
                    data.getDistance(),
                    data.getSpeed(),
                    data.getElevation(),
                    data.getCoordinates(),
                    user,
                    training
            );
        } else {
            activity = new Activity(
                    data.getTitle(),
                    data.getDescription(),
                    data.getTiming(),
                    data.getDistance(),
                    data.getSpeed(),
                    data.getElevation(),
                    data.getCoordinates(),
                    user
            );
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
        activity.setTiming(data.getTiming());
        activity.setDistance(data.getDistance());
        activity.setSpeed(data.getSpeed());
        activity.setElevation(data.getElevation());
        activity.setCoordinates(data.getCoordinates());

        return repository.save(activity);
    }

    public void destroy(UUID id, UUID userId) {
        this.show(id, userId);

        repository.deleteById(id);
    }
}