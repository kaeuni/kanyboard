package com.kanyboard.service;

import com.kanyboard.domain.Post;
import com.kanyboard.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int createPost(String title, String content, String author) {

        validatePost(title, content, author);
        return postRepository.save(title, content, author);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPost(int id) {
        return postRepository.findById(id);
    }

    public void updatePost(int id, String title, String content, String author) {
        Post post = findPostOrThrow(id);
        validatePost(title, content, author);
        postRepository.update(post, title, content, author);
    }

    public void deletePost(int id) {
        Post post = findPostOrThrow(id);
        postRepository.delete(post);
    }

    private void validatePost(String title, String content, String author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수 입력 항목입니다.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수 입력 항목입니다.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("작성자는 필수 입력 항목입니다.");
        }
    }

    private Post findPostOrThrow(int id) {
        Post post = findPost(id);
        if (post == null) {
            throw new IllegalArgumentException("존재하지 않는 게시글 번호입니다.");
        }
        return  post;
    }
}
