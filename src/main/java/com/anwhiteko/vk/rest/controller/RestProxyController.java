package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestProxyController {
    private final WebClient webClient;

    @GetMapping
    public String home() {
        return "Hello, world!";
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public List<Post> viewPosts() {
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                .block();
    }


}
