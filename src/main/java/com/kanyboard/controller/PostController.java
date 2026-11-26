package com.kanyboard.controller;

import com.kanyboard.domain.Post;
import com.kanyboard.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAllPosts();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        return postService.findPost(id);
    }
}
