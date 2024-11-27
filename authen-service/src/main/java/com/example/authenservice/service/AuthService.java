package com.example.authenservice.service;

import com.example.authenservice.payload.request.IntrospectRequest;
import com.example.authenservice.payload.request.LoginRequest;
import com.example.authenservice.payload.response.IntrospectResponse;
import com.example.authenservice.payload.response.RefreshTokenResponse;
import com.example.authenservice.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest loginRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest);

    RefreshTokenResponse refreshToken(IntrospectRequest refreshToken);
}
