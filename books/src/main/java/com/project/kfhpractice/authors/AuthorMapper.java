package com.project.kfhpractice.authors;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorEntity toAuthorEntity(AuthorCreateRequest request) {
        return new AuthorEntity(request.getFirstName(), request.getLastName());
    }


    public static AuthorResponseDto toAuthorResponseDto(AuthorEntity author) {
        return new AuthorResponseDto(author.getId(), author.getFirstName(), author.getLastName());
    }

    public static List<AuthorResponseDto> mapToAuthorResponseDto(List<AuthorEntity> authors) {
        return authors.stream()
                .map(AuthorMapper::toAuthorResponseDto)
                .collect(Collectors.toList());
    }
}
