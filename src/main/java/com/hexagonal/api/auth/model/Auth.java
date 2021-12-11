package com.hexagonal.api.auth.model;

import com.hexagonal.api.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Auth extends User implements UserDetails {
    public Auth(User user) {
        super(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.isActive(),
                user.getAvatar(),
                user.getEmailVerifiedAt(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new AuthRole(super.getRole()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.isActive();
    }
}
