package com.hexagonal.api.auth.repository;

import com.hexagonal.api.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository {
    User getAuthenticatedUser();
}