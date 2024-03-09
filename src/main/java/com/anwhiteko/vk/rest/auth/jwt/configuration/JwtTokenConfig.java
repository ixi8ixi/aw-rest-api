package com.anwhiteko.vk.rest.auth.jwt.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtTokenConfig(String key, String header, long validityTimeSeconds) {}
