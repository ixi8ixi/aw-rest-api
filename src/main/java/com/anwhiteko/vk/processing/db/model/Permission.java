package com.anwhiteko.vk.processing.db.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    VIEW_POSTS("VIEW_POSTS"),
    EDIT_POSTS("EDIT_POSTS"),
    VIEW_USERS("VIEW_USERS"),
    EDIT_USERS("EDIT_USERS"),
    VIEW_ALBUMS("VIEW_ALBUMS"),
    EDIT_ALBUMS("EDIT_ALBUMS");

    private final String permissionName;
}
