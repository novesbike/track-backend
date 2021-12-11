package com.hexagonal.api.services;

import com.hexagonal.api.exceptions.InvalidAttributeException;
import com.hexagonal.api.exceptions.ResourceNotFoundException;
import com.hexagonal.api.models.Role;
import com.hexagonal.api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> index() {
        return repository.findAll();
    }

    public Role store(Role role) {
        this.roleExists(role.getName());

        return repository.save(role);
    }

    public Role showByName(String name) {
        return repository.findByName(name.toUpperCase()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    public void roleExists(String name) {
        Optional<Role> userExists = repository.findByName(name.toUpperCase());

        if (userExists.isPresent()) {
            throw new InvalidAttributeException("This name is already registered to a role");
        }
    }

    public Role show(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    public Role update(UUID id, Role data) {
        Role role = this.show(id);

        this.roleExists(data.getName());

        role.setName(data.getName());
        role.setDescription(data.getDescription());

        return repository.save(role);
    }

    public void destroy(UUID id) {
        this.show(id);

        repository.deleteById(id);
    }
}