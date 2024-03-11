package com.anwhiteko.vk.rest.controller.dto;

public record Post(
        long id,
        long userId,
        String title,
        String body
) {}
