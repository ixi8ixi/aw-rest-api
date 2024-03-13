package com.anwhiteko.vk.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@EnableConfigurationProperties({ WebClientProperty.class })
public class WebClientConfiguration {
    private final String host;

    public WebClientConfiguration(WebClientProperty property) {
        this.host = property.host();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(host));
        return restTemplate;
    }
}
