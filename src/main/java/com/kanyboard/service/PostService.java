package com.kanyboard.service;

import com.kanyboard.domain.Post;
import com.kanyboard.exception.PostNotFoundException;
import com.kanyboard.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new PostNotFoundException("존재하지 않는 게시글입니다.");
        }
        return post;
    }

    public void updatePost(int id, String title, String content, String author) {
        Post post = findPost(id);
        validatePost(title, content, author);
        postRepository.update(post, title, content, author);
    }

    public void deletePost(int id) {
        Post post = findPost(id);
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
}
