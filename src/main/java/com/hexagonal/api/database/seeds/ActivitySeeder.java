package com.hexagonal.api.database.seeds;

import com.github.javafaker.Faker;
import com.hexagonal.api.models.*;
import com.hexagonal.api.repositories.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ActivitySeeder {
    public static Faker faker = new Faker();
    ActivityRepository repository;
    UserSeeder userSeeder;
    TrainingSeeder trainingSeeder;

    public Activity create() {
        User user = userSeeder.create();
        Training training = trainingSeeder.create();

        return repository.save(new Activity(faker.name().name(), faker.lorem().words(2).toString(), LocalTime.now(), faker.number().randomDouble(2, 0, 100), this.speed(), this.elevation(), this.coordinates(), user, training));
    }

    private List<ActivitySpeed> speed() {
        List<ActivitySpeed> data = new ArrayList<>();
        int x = (int) faker.number().randomNumber();

        for (int i = 0; i < x; i++) {
            data.add(new ActivitySpeed(faker.number().randomDouble(2, 0, 100), LocalDateTime.now()));
        }

        return data;
    }

    private List<ActivityElevation> elevation() {
        List<ActivityElevation> data = new ArrayList<>();
        int x = (int) faker.number().randomNumber();

        for (int i = 0; i < x; i++) {
            data.add(new ActivityElevation(faker.number().randomDouble(2, -100, 100), LocalDateTime.now()));
        }

        return data;
    }

    private List<ActivityCoordinates> coordinates() {
        List<ActivityCoordinates> data = new ArrayList<>();
        int x = (int) faker.number().randomNumber();

        for (int i = 0; i < x; i++) {
            data.add(new ActivityCoordinates(faker.number().randomDouble(2, -100, 100), faker.number().randomDouble(2, -100, 100), LocalDateTime.now()));
        }

        return data;
    }

    public void run(int x) {
        repository.saveAll(
                seeds(x)
        );
    }

    private List<Activity> seeds(int x) {
        List<Activity> activities = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            activities.add(create());
        }

        return activities;
    }
}