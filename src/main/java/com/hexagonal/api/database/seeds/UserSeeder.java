package com.hexagonal.api.database.seeds;

import com.github.javafaker.Faker;
import com.hexagonal.api.models.Role;
import com.hexagonal.api.models.User;
import com.hexagonal.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserSeeder {
    public static Faker faker = new Faker();
    UserRepository repository;
    RoleSeeder roleSeeder;
    BCryptPasswordEncoder bCrypt;

    public  User create() {
        Role role = roleSeeder.create();

        return repository.save(new User(faker.internet().emailAddress(), faker.random().hex(10), faker.name().name(), role,faker.random().nextBoolean()));
    }

    public void run() {
        if (repository.count() < 3) {
            repository.saveAll(
                    seeds()
            );
        }
    }

    public void run(int x) {
        repository.saveAll(
                seeds(x)
        );
    }

    private List<User> seeds() {
        String password = bCrypt.encode("12345678");

        Role adminRole = roleSeeder.getByName("ROLE_ADMIN");
        Role trainerRole = roleSeeder.getByName("ROLE_TRAINER");
        Role customerRole = roleSeeder.getByName("ROLE_CUSTOMER");

        User admin = new User("admin@admin.com", password, "Admin", adminRole);
        User trainer = new User("trainer@admin.com", password, "Trainer", trainerRole);
        User customer = new User("customer@admin.com", password, "Customer", customerRole);

        return new ArrayList<>(List.of(
                admin,
                trainer,
                customer
        ));
    }

    private List<User> seeds(int x) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            users.add(create());
        }

        return users;
    }
}