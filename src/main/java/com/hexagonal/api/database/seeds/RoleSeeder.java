package com.hexagonal.api.database.seeds;

import com.github.javafaker.Faker;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Role;
import com.hexagonal.api.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleSeeder {
    public static Faker faker = new Faker();
    RoleRepository repository;

    public  Role create() {
        return repository.save(new Role("ROLE_" + faker.lorem().word().toUpperCase(), faker.lorem().words(3).toString()));
    }

    public  Role create(String role) {
        return repository.save(new Role(role, role));
    }

    public Role getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
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

    private List<Role> seeds() {

        Role admin = this.create("ROLE_ADMIN");
        Role trainer = this.create("ROLE_TRAINER");
        Role customer = this.create("ROLE_CUSTOMER");

        return new ArrayList<>(List.of(
                admin,
                trainer,
                customer
        ));
    }

    private List<Role> seeds(int x) {
        List<Role> roles = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            roles.add(create());
        }

        return roles;
    }
}
