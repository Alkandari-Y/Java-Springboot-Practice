package com.project.authentication.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity createUser(RegisterRequest registerRequest) {
        UserEntity existingUser = findByUserName(registerRequest.username());
        if (existingUser != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User with username or email already exists");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password());

        UserEntity user = new UserEntity(
                registerRequest.username(),
                hashedPassword,
                registerRequest.email()
        );
        return userRepository.save(user);
    }
}
