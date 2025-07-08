package com.project.kfhpractice.authors;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorApiController {

    private final AuthorService authorService;

    public AuthorApiController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponseDto> getAllAuthors(
    ) {
        return AuthorMapper.mapToAuthorResponseDto(
                authorService.getAllAuthors()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorEntity saveAuthor(@Valid @RequestBody AuthorCreateRequest request) {
        return authorService.saveAuthor(AuthorMapper.toAuthorEntity(request));
    }

    @GetMapping("/details/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorEntity getAuthor(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @DeleteMapping("/details/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById(@PathVariable("authorId") Long authorId) throws ResponseStatusException {
        authorService.deleteAuthorById(authorId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorEntity> searchForAuthor(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName
    ) {
        return authorService.searchForAuthor(
                firstName != null ? firstName : "",
                lastName != null ? lastName : ""
        );
    }
}