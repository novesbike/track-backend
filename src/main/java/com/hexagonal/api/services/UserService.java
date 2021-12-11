package com.hexagonal.api.services;

import com.hexagonal.api.dtos.UserStoreDTO;
import com.hexagonal.api.dtos.UserUpdateDTO;
import com.hexagonal.api.exceptions.InvalidAttributeException;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Role;
import com.hexagonal.api.models.User;
import com.hexagonal.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCrypt;

    @Autowired
    public UserService(UserRepository repository, RoleService roleService, BCryptPasswordEncoder bCrypt) {
        this.repository = repository;
        this.roleService = roleService;
        this.bCrypt = bCrypt;
    }

    public List<User> index() {
        return repository.findAll();
    }

    public User store(UserStoreDTO data) {
        Optional<User> userExists = repository.findByEmail(data.getEmail().toLowerCase());

        if (userExists.isPresent()) {
            throw new InvalidAttributeException("This email is already registered to a user");
        }

        Role role = roleService.showByName(data.getRole().toUpperCase());

        var user = new User(
                data.getEmail().toLowerCase(),
                bCrypt.encode(data.getPassword()),
                data.getName(),
                role
        );

        return repository.save(user);
    }

    public User show(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User update(UUID id, UserUpdateDTO data) {
        User user = this.show(id);

        Role role = roleService.showByName(data.getRole().toUpperCase());

        user.setRole(role);

        return repository.save(user);
    }

    public void destroy(UUID id) {
        this.show(id);

        repository.deleteById(id);
    }

    public User activate(UUID id, boolean activate) {
        User user = this.show(id);

        user.setActive(activate);

        return repository.save(user);
    }
}
