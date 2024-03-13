package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.album.Album;
import com.anwhiteko.vk.rest.controller.dto.album.Photo;
import com.anwhiteko.vk.rest.controller.dto.post.Comment;
import com.anwhiteko.vk.rest.controller.dto.post.Post;
import com.anwhiteko.vk.rest.controller.dto.user.ToDo;
import com.anwhiteko.vk.rest.controller.dto.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedApiClient {
    private final RestTemplate restTemplate;  // todo add http template exceptions handling

    @Cacheable(value = "all_posts")
    public List<Post> viewPosts() {
        log.info("all posts");
        return Arrays.asList(get("/posts", Post[].class));
    }

    @Cacheable(value = "posts", key = "#id")
    public Post viewSinglePost(long id) {
        log.info("Singe get");
        return get("/posts/%s".formatted(id), Post.class);
    }

    @CachePut(value = "posts", key = "#result.id()")
    @CacheEvict(value = "all_posts", allEntries = true)
    public Post addPost(Post post) {
        log.info("Single post");
        return post("/posts", Post.class, post);
    }

    @CachePut(value = "posts", key = "#result.id()")
    @CacheEvict(value = "all_posts", allEntries = true)
    public Post updatePost(long id, Post post) {
        log.info("Single put");
        return put("/posts/%s".formatted(id), Post.class, post);
    }

    @Caching(evict = {
        @CacheEvict(value = "posts", key = "#id"),
        @CacheEvict(value = "comments", key = "#id"),
        @CacheEvict(value = "all_posts", allEntries = true)
    })
    public void deletePost(long id) {
        log.info("Single delete");
        delete("/posts/%s".formatted(id));
    }

    @Cacheable(value = "comments", key = "#id")
    public List<Comment> viewComments(long id) {
        log.info("comments");
        return Arrays.asList(get("/posts/%s/comments".formatted(id), Comment[].class));
    }

    @CacheEvict(value = "comments", key = "#id")
    public Comment addComment(long id, Comment comment) {
        return post("/posts/%s/comments".formatted(id), Comment.class, comment);
    }

    @Cacheable(value = "all_users")
    public List<User> viewUsers() {
        return Arrays.asList(get("/users", User[].class));
    }

    @Cacheable(value = "users", key = "#id")
    public User viewUser(long id) {
        return get("/users/%s".formatted(id), User.class);
    }

    @CachePut(value = "users", key = "#result.id()")
    @CacheEvict(value = "all_users", allEntries = true)
    public User addUser(User user) {
        return post("/users", User.class, user);
    }

    @CachePut(value = "users", key = "#result.id()")
    @CacheEvict(value = "all_users", allEntries = true)
    public User updateUser(long id, User user) {
        return put("/users/%s".formatted(id), User.class, user);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "todos", key = "#id"),
            @CacheEvict(value = "user_posts", key = "#id"),
            @CacheEvict(value = "user_albums", key = "#id"),
            @CacheEvict(value = "all_users", allEntries = true)
    })
    public void deleteUser(long id) {
        delete("/users/%s".formatted(id));
    }

    @Cacheable(value = "todos", key = "#id")
    public List<ToDo> viewToDos(long id) {
        return Arrays.asList(get("/users/%s/todos".formatted(id), ToDo[].class));
    }

    @CacheEvict(value = "todos", key = "#id")
    public ToDo addToDo(long id, ToDo todo) {
        return post("/users/%s/todos".formatted(id), ToDo.class, todo);
    }

    @Cacheable(value = "user_posts", key = "#id")
    public List<Post> viewUsersPosts(long id) {
        return Arrays.asList(get("/users/%s/posts".formatted(id), Post[].class));
    }

    @Cacheable(value = "user_albums", key = "#id")
    public List<Album> viewUsersAlbums(long id) {
        return Arrays.asList(get("/users/%s/albums".formatted(id), Album[].class));
    }

    @Cacheable(value = "all_albums")
    public List<Album> viewAlbums() {
        return Arrays.asList(get("/albums", Album[].class));
    }

    @Cacheable(value = "albums", key = "#id")
    public Album viewAlbum(long id) {
        return get("/alnums/%s".formatted(id), Album.class);
    }

    @CachePut(value = "albums", key = "#result.id()")
    @CacheEvict(value = "all_albums", allEntries = true)
    public Album addAlbum(Album album) {
        return post("/albums", Album.class, album);
    }

    @CachePut(value = "albums", key = "#result.id()")
    @CacheEvict(value = "all_albums", allEntries = true)
    public Album updateAlbum(long id, Album album) {
        return put("/albums/%s".formatted(id), Album.class, album);
    }

    @Caching(evict = {
            @CacheEvict(value = "albums", key = "#id"),
            @CacheEvict(value = "all_albums", allEntries = true),
            @CacheEvict(value = "photos", key = "#id")
    })
    public void deleteAlbum(long id) {
        delete("/albums/%s".formatted(id));
    }

    @Cacheable(value = "photos", key = "#id")
    public List<Photo> viewAlbumPhotos(long id) {
        return Arrays.asList(get("/albums/%s/photos".formatted(id), Photo[].class));
    }

    @CacheEvict(value = "photos", key = "#id")
    public Photo addPhoto(long id, Photo photo) {
        return post("/albums/%s/photos".formatted(id), Photo.class, photo);
    }

    @Scheduled(fixedDelayString = "${caching.timeout}")
    @Caching(evict = {
            @CacheEvict(value = "photos", allEntries = true),
            @CacheEvict(value = "todos", allEntries = true),
            @CacheEvict(value = "comments", allEntries = true),
            @CacheEvict(value = "user_posts", allEntries = true),
            @CacheEvict(value = "user_albums", allEntries = true),
            @CacheEvict(value = "posts", allEntries = true),
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "albums", allEntries = true),
            @CacheEvict(value = "all_posts", allEntries = true),
            @CacheEvict(value = "all_users", allEntries = true),
            @CacheEvict(value = "all_albums", allEntries = true),
    })
    public void invalidateCache() {}

    private <T> T get(String uri, Class<T> clazz) {
        try {
            return restTemplate.getForEntity(uri, clazz).getBody();
        } catch (HttpStatusCodeException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }

    private <T> T post(String uri, Class<T> clazz, T content) {
        try {
            HttpEntity<T> entity = new HttpEntity<>(content);
            ResponseEntity<T> response = restTemplate.postForEntity(uri, entity, clazz);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }
    
    private <T> T put(String uri, Class<T> clazz, T content) {
        try {
            HttpEntity<T> request = new HttpEntity<>(content);
            HttpEntity<T> result = restTemplate.exchange(uri, HttpMethod.PUT, request, clazz);
            return result.getBody();
        } catch (HttpStatusCodeException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }
    
    private void delete(String uri) {
        try {
            restTemplate.delete(uri);
        } catch (HttpStatusCodeException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }
}
