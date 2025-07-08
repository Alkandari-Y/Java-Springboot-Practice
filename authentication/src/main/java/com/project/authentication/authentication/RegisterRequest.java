package com.project.authentication.authentication;

public record RegisterRequest(
        String username,
        String password,
        String email
) {
}
