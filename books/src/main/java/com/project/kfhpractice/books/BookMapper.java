package com.project.kfhpractice.books;

import com.project.kfhpractice.authors.AuthorEntity;

import java.awt.print.Book;

public class BookMapper {
    public static BookEntity toBookEntity(BookCreateRequest request, AuthorEntity author) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(author);
        bookEntity.setDescription(request.getDescription());

        return bookEntity;
    }
}
