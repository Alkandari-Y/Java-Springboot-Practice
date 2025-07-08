package com.project.authentication.authentication;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationApiController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    AuthenticationApiController(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public JwtTokenPairResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserEntity user =  userService.createUser(registerRequest);
        return jwtService.generateTokenPair(
                user,
                user.getRoles().stream().map(RoleEntity::getName).toList()
        );
    }

    @PostMapping("/login")
    public JwtTokenPairResponse login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.username(), loginRequestDto.password()
        );

        var authentication = authenticationManager.authenticate(authToken);
        var userDetails = authentication.getPrincipal();
        if (userDetails == null) {
            throw new RuntimeException("User details is null");
        }
        UserEntity user = userService.findByUserName(loginRequestDto.username());
        JwtTokenPairResponse tokenPairResponse = jwtService.generateTokenPair(
                user,
                user.getRoles().stream().map(RoleEntity::getName).toList()
        );
        return tokenPairResponse;
    }

}