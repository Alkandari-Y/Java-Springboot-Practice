package com.project.authentication.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @JsonProperty("username")
        @NotNull(message = "Username is required")
        String username,
        @JsonProperty("password")
        @NotNull(message = "Password is required")
        String password
) {
}
