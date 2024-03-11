package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.Comment;
import com.anwhiteko.vk.rest.controller.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CachedApiClient {
    private final WebClient webClient;

    public Mono<List<Post>> viewPosts() {
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {})
                .cache();
    }

    public Mono<Post> viewSinglePost(int id) {
        return webClient
                .get()
                .uri("/posts/" + id)
                .retrieve()
                .bodyToMono(Post.class)
                .cache();
    }

    public Mono<Post> addPost(Post post) {
        return webClient
                .post()
                .uri("/posts")
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class)
                .cache();
    }

    public Mono<Post> updatePost(int id, Post post) {
        return webClient
                .put()
                .uri("/posts/" + id)
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class)
                .cache();
    }

    public void deletePost(long id) {
        webClient
                .delete()
                .uri("/posts/" + id)
                .retrieve()
                .toEntity(Void.class)
                .cache();
    }

    public Mono<List<Comment>> viewComments(int id) {
        return webClient
                .get()
                .uri("/posts/" + id + "/comments")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {})
                .cache();
    }

    public Mono<Comment> addComment(int id, Comment comment) {
        return webClient
                .post()
                .uri("/posts/" + id + "/comments")
                .body(Mono.just(comment), Comment.class)
                .retrieve()
                .bodyToMono(Comment.class)
                .cache();
    }
}
