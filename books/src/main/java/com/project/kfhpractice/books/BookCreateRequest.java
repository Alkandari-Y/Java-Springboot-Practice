package com.project.kfhpractice.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class BookCreateRequest {

    @JsonProperty("title")
    @NotEmpty(message = "Title is required")
    @Length(max = 255, message = "Title must not exceed 255 characters")
    @Size(min = 2, message = "Title must be at least 2 characters")
    private String title;


    @JsonProperty("authorId")
    @NotNull(message = "Author id is required")
    @Min(value = 1, message = "Author id must be greater than 0")
    private Long authorId;

    @JsonProperty("description")
    @NotEmpty(message = "Description is required")
    @Length(max = 255, message = "Description must not exceed 255 characters")
    @Length(min = 10, message = "Description must be at least 10 characters")
    private String description;

    public BookCreateRequest() {}

    public BookCreateRequest(String title, Long authorId, String description) {
        this.title = title;
        this.authorId = authorId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long author) {
        this.authorId = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
