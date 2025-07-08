package com.project.kfhpractice.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class JwtAuthProvider {

    private final String authServiceURL;

    public JwtAuthProvider(@Value("${authService.url}") String authServiceURL) {
        this.authServiceURL = authServiceURL;
    }

    public ValidateTokenResponse validateToken(String token) {
        try {
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            var requestEntity = new HttpEntity<>(null, headers);

            var responseEntity = new RestTemplate().postForEntity(
                    authServiceURL + "/api/v1/auth/validate",
                    requestEntity,
                    ValidateTokenResponse.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Token validation failed with status: " + responseEntity.getStatusCode());
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Error validating token with authentication service: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during token validation: " + e.getMessage(), e);
        }
    }
}