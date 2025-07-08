package com.project.authentication.authentication;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKeyString;

    @Value("${jwt.accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    public JwtTokenPairResponse generateTokenPair(UserEntity user, List<String> authorities) {
        return new JwtTokenPairResponse(
                generateAccessToken(user, authorities),
                generateRefreshToken(user, authorities)
        );
    }

    public String generateAccessToken(UserEntity user, List<String> authorities) {
        return generateToken(user, accessTokenExpirationMs, "access", authorities);
    }

    public String generateRefreshToken(UserEntity user, List<String> authorities) {
        return generateToken(user, refreshTokenExpirationMs, "refresh", authorities);
    }

    private String generateToken(UserEntity user, long expirationMs, String type, List<String> authorities) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("roles", authorities)
                .claim("type", type)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public boolean isValidTokenType(String token, String expectedType) {
        try {
            Claims claims = parseToken(token);
            String type = claims.get("type", String.class);
            return type != null && type.equals(expectedType);
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid token
        }
    }

    private Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }
}