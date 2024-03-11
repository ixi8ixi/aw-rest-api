package com.anwhiteko.vk.rest.controller.dto.album;

public record Photo(
        long id,
        long albumId,
        String title,
        String url,
        String thumbnailUrl
) {}
