package com.hexagonal.api.database.seeds;

import com.github.javafaker.Faker;
import com.hexagonal.api.models.Group;
import com.hexagonal.api.models.Training;
import com.hexagonal.api.repositories.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TrainingSeeder {
    public static Faker faker = new Faker();
    TrainingRepository repository;
    GroupSeeder groupSeeder;

    public  Training create() {
        Group group = groupSeeder.create();

        return repository.save(new Training(faker.name().name(), faker.lorem().words(2).toString(), group));
    }

    public void run(int x) {
        repository.saveAll(
                seeds(x)
        );
    }

    private List<Training> seeds(int x) {
        List<Training> trainings = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            trainings.add(create());
        }

        return trainings;
    }
}