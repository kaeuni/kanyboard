package com.kanyboard.controller;

import com.kanyboard.domain.Post;
import com.kanyboard.dto.PostCreateRequest;
import com.kanyboard.dto.PostUpdateRequest;
import com.kanyboard.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Integer> createPost(@Valid @RequestBody PostCreateRequest request) {
        int id = postService.createPost(
                request.getTitle(), request.getContent(), request.getAuthor());
        return ResponseEntity.status(201).body(id);
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable int id) {
        Post post =  postService.findPost(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable int id, @Valid @RequestBody PostUpdateRequest request) {
        postService.updatePost(id, request.getTitle(), request.getContent(), request.getAuthor());
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.status(204).build();
    }
}
