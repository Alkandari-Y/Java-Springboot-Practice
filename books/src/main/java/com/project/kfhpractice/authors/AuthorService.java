package com.project.kfhpractice.authors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorEntity getAuthorById(Long authorId) throws ResponseStatusException {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);

        if (author.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Author with id %d was not found", authorId)
            );
        }

        return author.get();
    }

    public AuthorEntity saveAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    public void deleteAuthor(AuthorEntity authorEntity) {
        authorRepository.delete(authorEntity);
    }

    public void deleteAuthorById(Long authorId) throws ResponseStatusException {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);

        if (author.isPresent()) {
            deleteAuthor(author.get());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Author with id %d was not found", authorId)
            );
        }
    }

    public List<AuthorEntity> searchForAuthor(String firstName, String lastName) {
        if ((firstName == null || firstName.isEmpty()) && (lastName == null || lastName.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one of firstName or lastName must be provided.");
        }
        return authorRepository.findByFirstNameContainingOrLastNameContaining(
                firstName,
                lastName
        );
    }
}