package com.anwhiteko.vk.rest.controller.dto;

public record Comment(
        long id,
        long postId,
        String name,
        String email,
        String body
) {}
