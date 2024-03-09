package com.anwhiteko.vk.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest-api")
public record WebClientProperty(String host, int timeout) {}
