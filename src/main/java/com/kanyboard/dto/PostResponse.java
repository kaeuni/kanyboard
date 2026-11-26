package com.kanyboard.dto;

import com.kanyboard.domain.Post;

public class PostResponse {
    private final int id;
    private final String title;
    private final String content;
    private final String author;

    public PostResponse(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }
}
