package com.project.kfhpractice.providers;

import java.util.List;

public record ValidateTokenResponse(
        Long userId,
        List<String> roles,
        String username
) {
}

