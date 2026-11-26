package com.kanyboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostCreateRequest {
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 200, message = "제목은 200자 이하로 입력해야 합니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;
    @NotBlank(message = "작성자는 필수 입력 항목입니다.")
    @Size(max = 50, message = "작성자는 50자 이하로 입력해야 합니다.")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
