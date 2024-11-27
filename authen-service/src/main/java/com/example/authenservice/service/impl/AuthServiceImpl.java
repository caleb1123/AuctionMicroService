package com.example.authenservice.service.impl;

import com.example.authenservice.entity.RefreshToken;
import com.example.authenservice.entity.User;
import com.example.authenservice.exception.AppException;
import com.example.authenservice.exception.ErrorCode;
import com.example.authenservice.jwt.Jwt;
import com.example.authenservice.payload.request.IntrospectRequest;
import com.example.authenservice.payload.request.LoginRequest;
import com.example.authenservice.payload.response.IntrospectResponse;
import com.example.authenservice.payload.response.RefreshTokenResponse;
import com.example.authenservice.payload.response.TokenResponse;
import com.example.authenservice.repository.RefreshTokenRepository;
import com.example.authenservice.repository.UserRepository;
import com.example.authenservice.service.AuthService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    Jwt jwt;
    UserRepository userRepository;
    RefreshTokenRepository refreshTokenRepository;
    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository
                .findUserByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        RefreshToken refreshTokenold = refreshTokenRepository.findRefreshTokenByUserName(user.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_REFRESH_TOKEN));
        refreshTokenRepository.delete(refreshTokenold);

        var tokenPair = jwt.generateTokens(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(tokenPair.refreshToken().token());
        refreshToken.setExpiryDate(tokenPair.refreshToken().expiryDate().toInstant());
        refreshToken.setUserName(user.getUserName());
        refreshTokenRepository.save(refreshToken);

        return TokenResponse.builder()
                .accessToken(tokenPair.accessToken().token())
                .refreshToken(tokenPair.refreshToken().token())
                .accessExpiryTime(tokenPair.accessToken().expiryDate())
                .refreshExpiryTime(tokenPair.refreshToken().expiryDate())
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        var token = introspectRequest.getToken();
        boolean isValid = true;

        try {
            jwt.verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public RefreshTokenResponse refreshToken(IntrospectRequest refreshToken) {
        RefreshToken token = refreshTokenRepository.findRefreshTokenByToken(refreshToken.getToken())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_REFRESH_TOKEN));
        User user = userRepository.findUserByUserName(token.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var accessToken = jwt.generateAccessToken(user);
        return RefreshTokenResponse.builder()
                .accessToken(accessToken.token())
                .accessExpiryTime(accessToken.expiryDate())
                .build();

    }
}
