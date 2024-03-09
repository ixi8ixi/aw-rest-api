package com.anwhiteko.vk.processing.db.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Role {
    ROLE_ADMIN(EnumSet.allOf(Permission.class)),
    ROLE_POSTS(EnumSet.of(Permission.EDIT_POSTS, Permission.VIEW_POSTS)),
    ROLE_USERS(EnumSet.of(Permission.EDIT_USERS, Permission.VIEW_USERS)),
    ROLE_ALBUMS(EnumSet.of(Permission.EDIT_ALBUMS, Permission.VIEW_ALBUMS));

    private final Set<Permission> permissions;

    Role(EnumSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission.name()))
                .toList();
    }
}
