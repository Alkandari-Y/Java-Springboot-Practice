package com.project.authentication.authentication;


public record JwtTokenPairResponse (
        String access,
        String refresh
) { }