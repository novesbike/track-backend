package com.hexagonal.api.auth.service;

import com.hexagonal.api.auth.model.Auth;
import com.hexagonal.api.auth.repository.AuthRepository;
import com.hexagonal.api.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthRepository {
    public static Auth authenticated() {
        try {
            return (Auth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public User getAuthenticatedUser() {
        return authenticated();
    }
}