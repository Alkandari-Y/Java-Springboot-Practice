package com.project.kfhpractice.books;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookApiController {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookEntity saveBook(@Valid @RequestBody BookCreateRequest bookEntity) {
        return bookService.saveBook(bookEntity);
    }

    @GetMapping("/details/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookEntity getBook(@PathVariable("bookId") Long bookId) {

        Optional<BookEntity> book = bookService.getBookById(bookId);

        if (book.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Book with id %d was not found", bookId)
            );
        }

        return book.get();
    }

    @DeleteMapping("/details/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable("bookId") Long bookId) {
        bookService.deleteBookById(bookId);
    }
}
