package com.anwhiteko.vk.processing.db;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "users")
@Builder
public class UserEntity {
    public enum Role {
        ROLE_ADMIN, ROLE_POSTS, ROLE_USERS, ROLE_ALBUMS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
