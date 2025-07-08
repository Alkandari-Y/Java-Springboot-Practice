package com.project.kfhpractice.books;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.kfhpractice.authors.AuthorEntity;
import jakarta.persistence.*;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,  length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private AuthorEntity author;

    @Column(nullable = false ,length = 255)
    private String description;

    public BookEntity(
            String title,
            AuthorEntity author,
            String description
    ) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public BookEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookEntity(" +
                "id=" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
            ")";
    }
}
