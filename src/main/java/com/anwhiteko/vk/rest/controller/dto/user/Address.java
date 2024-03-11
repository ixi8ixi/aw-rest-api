package com.anwhiteko.vk.rest.controller.dto.user;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {}
