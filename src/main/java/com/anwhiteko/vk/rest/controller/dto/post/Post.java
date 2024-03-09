package com.anwhiteko.vk.rest.controller.dto.post;

public record Post(
        long id,
        long userId,
        String title,
        String body
) {}
