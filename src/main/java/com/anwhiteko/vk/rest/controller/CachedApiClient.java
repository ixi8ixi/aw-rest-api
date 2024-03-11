package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.album.Album;
import com.anwhiteko.vk.rest.controller.dto.post.Comment;
import com.anwhiteko.vk.rest.controller.dto.post.Post;
import com.anwhiteko.vk.rest.controller.dto.user.ToDo;
import com.anwhiteko.vk.rest.controller.dto.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CachedApiClient {
    private final WebClient webClient;

    public Flux<Post> viewPosts() {
        return getFlux("/posts", Post.class);
    }

    public Mono<Post> viewSinglePost(long id) {
        return get("/posts/%s".formatted(id), Post.class);
    }

    public Mono<Post> addPost(Post post) {
        return post("/posts", Post.class, post);
    }

    public Mono<Post> updatePost(long id, Post post) {
        return put("/posts/%s".formatted(id), Post.class, post);
    }

    public void deletePost(long id) {
        delete("/posts/%s".formatted(id));
    }

    public Flux<Comment> viewComments(long id) {
        return getFlux("/posts/%s/comments".formatted(id), Comment.class);
    }

    public Mono<Comment> addComment(long id, Comment comment) {
        return post("/posts/%s/comments".formatted(id), Comment.class, comment);
    }

    public Flux<User> viewUsers() {
        return getFlux("/users", User.class);
    }

    public Mono<User> viewUser(long id) {
        return get("/users/%s".formatted(id), User.class);
    }

    public Mono<User> addUser(User user) {
        return post("/users", User.class, user);
    }

    public Mono<User> updateUser(long id, User user) {
        return put("/users/%s".formatted(id), User.class, user);
    }

    public void deleteUser(long id) {
        delete("/users/%s".formatted(id));
    }

    public Flux<ToDo> viewToDos(long id) {
        return getFlux("/users/%s/todos".formatted(id), ToDo.class);
    }

    public Mono<ToDo> addToDo(long id, ToDo todo) {
        return post("/users/%s/todos".formatted(id), ToDo.class, todo);
    }

    public Flux<Post> viewUsersPosts(long id) {
        return getFlux("/users/%s/posts".formatted(id), Post.class);
    }

    public Flux<Album> viewUsersAlbums(long id) {
        return getFlux("/users/%s/albums".formatted(id), Album.class);
    }

    private <T> Mono<T> get(String uri, Class<T> clazz) {
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(clazz)
                .cache();
    }

    private <T> Flux<T> getFlux(String uri, Class<T> clazz) {
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(clazz)
                .cache();
    }

    private <T> Mono<T> post(String uri, Class<T> clazz, T content) {
        return webClient
                .post()
                .uri(uri)
                .body(Mono.just(content), clazz)
                .retrieve()
                .bodyToMono(clazz)
                .cache();
    }
    
    private <T> Mono<T> put(String uri, Class<T> clazz, T content) {
        return webClient
                .put()
                .uri(uri)
                .body(Mono.just(content), clazz)
                .retrieve()
                .bodyToMono(clazz)
                .cache();
    }
    
    private void delete(String uri) {
        webClient
                .delete()
                .uri(uri)
                .retrieve()
                .toEntity(Void.class)
                .cache();
    }
}
