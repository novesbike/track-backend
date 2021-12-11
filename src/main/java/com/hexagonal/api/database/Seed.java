package com.hexagonal.api.database;

import com.hexagonal.api.database.seeds.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Seed implements ApplicationRunner {
    RoleSeeder roleSeeder;
    UserSeeder userSeeder;
    GroupSeeder groupSeeder;
    TrainingSeeder trainingSeeder;
    ActivitySeeder activitySeeder;

    @Override
    public void run(ApplicationArguments args) {
        roleSeeder.run();
        userSeeder.run();
        roleSeeder.run(1);
        userSeeder.run(1);
        groupSeeder.run(1);
        trainingSeeder.run(1);
        activitySeeder.run(1);
    }
}
