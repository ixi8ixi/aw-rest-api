package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.rest.controller.dto.album.Album;
import com.anwhiteko.vk.rest.controller.dto.album.Photo;
import com.anwhiteko.vk.rest.controller.dto.post.Comment;
import com.anwhiteko.vk.rest.controller.dto.post.Post;
import com.anwhiteko.vk.rest.controller.dto.user.ToDo;
import com.anwhiteko.vk.rest.controller.dto.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestProxyController {
    private final CachedApiClient cachedApiClient;

    @GetMapping
    public String home(Authentication authentication) { // todo проверять наличие аутентификации в
        return "Hello, World!";
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public List<Post> viewPosts() {
        return cachedApiClient.viewPosts();
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public Post viewSinglePost(HttpServletRequest httpServletRequest, @PathVariable long id) {
        return cachedApiClient.viewSinglePost(id);
    }

    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public Post addPost(@RequestBody Post post) {
        return cachedApiClient.addPost(post);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize(("hasAuthority('EDIT_POSTS')"))
    public Post updatePost(@PathVariable long id, @RequestBody Post post) {
        return cachedApiClient.updatePost(id, post);
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public void deletePost(@PathVariable long id) {
        cachedApiClient.deletePost(id);
    }

    @GetMapping("/posts/{id}/comments")
    @PreAuthorize("hasAuthority('VIEW_POSTS')")
    public List<Comment> viewComments(@PathVariable long id) {
        return cachedApiClient.viewComments(id);
    }

    @PostMapping("/posts/{id}/comments")
    @PreAuthorize("hasAuthority('EDIT_POSTS')")
    public Comment addComment(@PathVariable long id, @RequestBody Comment comment) {
        return cachedApiClient.addComment(id, comment);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public List<User> viewUsers() {
        return cachedApiClient.viewUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public User viewUser(@PathVariable long id) {
        return cachedApiClient.viewUser(id);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public User addUser(@RequestBody User user) {
        return cachedApiClient.addUser(user);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return cachedApiClient.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public void deleteUser(@PathVariable long id) {
        cachedApiClient.deleteUser(id);
    }

    @GetMapping("/users/{id}/todos")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public List<ToDo> viewToDos(@PathVariable long id) {
        return cachedApiClient.viewToDos(id);
    }

    @PostMapping("/users/{id}/todos")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public ToDo addToDo(@PathVariable long id, ToDo todo) {
        return cachedApiClient.addToDo(id, todo);
    }

    @GetMapping("/users/{id}/posts")
    @PreAuthorize("hasAuthority('VIEW_USERS') and hasAuthority('VIEW_POSTS')")
    public List<Post> viewUsersPosts(@PathVariable long id) {
        return cachedApiClient.viewUsersPosts(id);
    }

    @GetMapping("/users/{id}/albums")
    @PreAuthorize("hasAuthority('VIEW_USERS') and hasAuthority('VIEW_ALBUMS')")
    public List<Album> viewUsersAlbums(@PathVariable long id) {
        return cachedApiClient.viewUsersAlbums(id);
    }

    @GetMapping("/albums")
    @PreAuthorize("hasAuthority('VIEW_ALBUMS')")
    public List<Album> viewAlbums() {
        return cachedApiClient.viewAlbums();
    }

    @GetMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('VIEW_ALBUMS')")
    public Album viewAlbum(@PathVariable long id) {
        return cachedApiClient.viewAlbum(id);
    }

    @PostMapping("/albums")
    @PreAuthorize("hasAuthority('EDIT_ALBUMS')")
    public Album addAlbum(@RequestBody Album album) {
        return cachedApiClient.addAlbum(album);
    }

    @PutMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('EDIT_ALBUMS')")
    public Album updateAlbum(@PathVariable long id, @RequestBody Album album) {
        return cachedApiClient.updateAlbum(id, album);
    }

    @DeleteMapping("/albums/{id}")
    @PreAuthorize("hasAuthority('EDIT_ALBUMS')")
    public void deleteAlbum(@PathVariable long id) {
        cachedApiClient.deleteAlbum(id);
    }

    @GetMapping("/albums/{id}/photos")
    @PreAuthorize("hasAuthority('VIEW_ALBUMS')")
    public List<Photo> viewAlbumPhotos(@PathVariable long id) {
        return cachedApiClient.viewAlbumPhotos(id);
    }

    @PostMapping("/albums/{id}/photos")
    @PreAuthorize("hasAuthority('EDIT_ALBUMS')")
    public Photo addPhoto(@PathVariable long id, @RequestBody Photo photo) {
        return cachedApiClient.addPhoto(id, photo);
    }
}
