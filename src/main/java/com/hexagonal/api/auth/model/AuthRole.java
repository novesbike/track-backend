package com.hexagonal.api.auth.model;

import com.hexagonal.api.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class AuthRole extends Role implements GrantedAuthority {

    public AuthRole( String name, String description) {
        super(name, description);
    }

    public AuthRole(Role role) {
        this( role.getName(), role.getDescription());
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}