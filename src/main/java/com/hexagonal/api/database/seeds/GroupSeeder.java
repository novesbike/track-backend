package com.hexagonal.api.database.seeds;

import com.github.javafaker.Faker;
import com.hexagonal.api.enums.GroupLevel;
import com.hexagonal.api.models.Group;
import com.hexagonal.api.models.User;
import com.hexagonal.api.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class GroupSeeder {
    public static Faker faker = new Faker();
    GroupRepository repository;
    UserSeeder userSeeder;

    public  Group create() {
        int level = new Random().nextInt(GroupLevel.values().length);

        User trainer = userSeeder.create();

        return repository.save(new Group(faker.name().name(), faker.lorem().words(2).toString(), GroupLevel.values()[level], trainer));
    }

    public void run(int x) {
        repository.saveAll(
                seeds(x)
        );
    }

    private List<Group> seeds(int x) {
        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            groups.add(create());
        }

        return groups;
    }
}