package com.anwhiteko.vk.security;

import com.anwhiteko.vk.processing.db.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> auth;
    private final boolean active;

    public SecurityUser(String username, String password, List<GrantedAuthority> auth,  boolean active) {
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public static UserDetails fromUserEntity(UserEntity entity) {
        return new SecurityUser(entity.getUsername(), entity.getPassword(), entity.getRole().getAuthorities(), true);
    }
}
