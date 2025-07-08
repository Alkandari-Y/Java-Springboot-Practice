package com.project.kfhpractice.books;

import com.project.kfhpractice.authors.AuthorEntity;
import com.project.kfhpractice.authors.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(
            BookRepository bookRepository,
            AuthorService authorService
    ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public BookEntity saveBook(BookCreateRequest request) {
        AuthorEntity author = authorService.getAuthorById(request.getAuthorId());
        BookEntity book = BookMapper.toBookEntity(request, author);
        return bookRepository.save(book);
    }

    public void deleteBook(BookEntity bookEntity) {
        bookRepository.delete(bookEntity);
    }

    public void deleteBookById(Long bookId) throws ResponseStatusException {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            deleteBook(book.get());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Book with id %d was not found", bookId)
            );
        }
    }
}
