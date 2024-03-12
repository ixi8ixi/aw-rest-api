package com.anwhiteko.vk.processing.db.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Role { // todo прочитать как маппятся enumы
    ROLE_ADMIN(EnumSet.allOf(Permission.class)),
    ROLE_POSTS(EnumSet.of(Permission.EDIT_POSTS, Permission.VIEW_POSTS)),
    ROLE_USERS(EnumSet.of(Permission.EDIT_USERS, Permission.VIEW_USERS)),
    ROLE_ALBUMS(EnumSet.of(Permission.EDIT_ALBUMS, Permission.VIEW_ALBUMS)),
    ROLE_TOTAL_OBSERVER(EnumSet.of(Permission.VIEW_POSTS, Permission.VIEW_USERS, Permission.VIEW_ALBUMS)),
    ROLE_POSTS_OBSERVER(EnumSet.of(Permission.VIEW_POSTS)),
    ROLE_USERS_OBSERVER(EnumSet.of(Permission.VIEW_USERS)),
    ROLE_ALBUMS_OBSERVER(EnumSet.of(Permission.VIEW_ALBUMS));

    private final Set<Permission> permissions;

    Role(EnumSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<GrantedAuthority> getAuthorities() {
        return permissions.stream() // fixme Переделать permission.name()
                .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission.name()))
                .toList();
    }
}
