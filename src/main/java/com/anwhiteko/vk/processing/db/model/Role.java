package com.anwhiteko.vk.processing.db.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Role {
    /**
     * Has all existing permissions.
     */
    ROLE_ADMIN(EnumSet.allOf(Permission.class)),

    /**
     * Has permissions to view and edit posts.
     */
    ROLE_POSTS(EnumSet.of(Permission.EDIT_POSTS, Permission.VIEW_POSTS)),

    /**
     * Has permissions to view and edit users.
     */
    ROLE_USERS(EnumSet.of(Permission.EDIT_USERS, Permission.VIEW_USERS)),

    /**
     * Has permissions to view and edit albums.
     */
    ROLE_ALBUMS(EnumSet.of(Permission.EDIT_ALBUMS, Permission.VIEW_ALBUMS)),

    /**
     * Has permissions to view posts, users and albums.
     */
    ROLE_TOTAL_OBSERVER(EnumSet.of(Permission.VIEW_POSTS, Permission.VIEW_USERS, Permission.VIEW_ALBUMS)),

    /**
     * Has permission to view posts.
     */
    ROLE_POSTS_OBSERVER(EnumSet.of(Permission.VIEW_POSTS)),

    /**
     * Has permission to view users.
     */
    ROLE_USERS_OBSERVER(EnumSet.of(Permission.VIEW_USERS)),

    /**
     * Has permission to view albums.
     */
    ROLE_ALBUMS_OBSERVER(EnumSet.of(Permission.VIEW_ALBUMS));

    private final Set<Permission> permissions;

    Role(EnumSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission.getPermissionName()))
                .toList();
    }
}
