package com.project.authentication.authentication;

import java.util.List;

public record ValidateTokenResponse(
        Long userId,
        List<String> roles,
        String username
) {
}
