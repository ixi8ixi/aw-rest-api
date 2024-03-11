package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.Comment;
import com.anwhiteko.vk.rest.controller.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestProxyController {
    private final CachedApiClient cachedApiClient;

    @GetMapping
    public String home() {
        return "Hello, world!";
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public Mono<List<Post>> viewPosts() {
        return cachedApiClient.viewPosts();
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public Mono<Post> viewSinglePost(@PathVariable int id) {
        return cachedApiClient.viewSinglePost(id);
    }

    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public Mono<Post> addPost(@RequestBody Post post) {
        return cachedApiClient.addPost(post);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize(("hasAuthority('EDIT_POSTS')"))
    public Mono<Post> updatePost(@PathVariable int id, @RequestBody Post post) {
        return cachedApiClient.updatePost(id, post);
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public void deletePost(@PathVariable long id) {
        cachedApiClient.deletePost(id);
    }

    @GetMapping("posts/{id}/comments")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public Mono<List<Comment>> viewComments(@PathVariable int id) {
        return cachedApiClient.viewComments(id);
    }

    @PostMapping("posts/{id}/comments")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public Mono<Comment> addComment(@PathVariable int id, @RequestBody Comment comment) {
        return cachedApiClient.addComment(id, comment);
    }
}
