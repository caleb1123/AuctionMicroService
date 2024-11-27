package com.example.authenservice.controller;

import com.example.authenservice.payload.request.IntrospectRequest;
import com.example.authenservice.payload.request.LoginRequest;
import com.example.authenservice.payload.response.ApiResponse;
import com.example.authenservice.payload.response.RefreshTokenResponse;
import com.example.authenservice.payload.response.TokenResponse;
import com.example.authenservice.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        var result = authService.login(loginRequest);
        return ApiResponse.<TokenResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<Boolean> introspect(@RequestBody IntrospectRequest token) {
        var result = authService.introspect(token);
        return ApiResponse.<Boolean>builder()
                .result(result.isValid())
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refreshToken(@RequestBody IntrospectRequest refreshToken) {
        var result = authService.refreshToken(refreshToken);
        return ApiResponse.<RefreshTokenResponse>builder()
                .result(result)
                .build();
    }
}
