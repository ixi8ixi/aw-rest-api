package com.anwhiteko.vk.rest.controller.dto.user;

public record ToDo(
        long userId,
        long id,
        String title,
        boolean completed
) {}
