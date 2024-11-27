package com.example.authenservice.jwt;

import com.example.authenservice.entity.User;
import com.example.authenservice.exception.AppException;
import com.example.authenservice.exception.ErrorCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
@Component
public class Jwt {
    @Value("${jwt.secret}")
    private String signerKey;

    @Value("${jwt.app.jwt-access-expiration-milliseconds}")
    private long accessExpirationMillis;

    @Value("${jwt.app.jwt-refresh-expiration-milliseconds}")
    private long refreshExpirationMillis;

    public TokenPair generateTokens(User user) {
        // Generate Access Token
        TokenInfo accessToken = generateToken(user, accessExpirationMillis);

        // Generate Refresh Token
        TokenInfo refreshToken = generateToken(user, refreshExpirationMillis);

        return new TokenPair(accessToken, refreshToken);
    }

    private TokenInfo generateToken(User user, long expirationMillis) {
        try {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            Date issueTime = new Date();
            Date expiryTime = new Date(issueTime.getTime() + expirationMillis);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUserName())
                    .issuer("devteria.com")
                    .issueTime(issueTime)
                    .expirationTime(expiryTime)
                    .jwtID(UUID.randomUUID().toString())
                    .claim("userId", user.getUserId())
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            signedJWT.sign(new MACSigner(signerKey.getBytes()));

            return new TokenInfo(signedJWT.serialize(), expiryTime);
        } catch (JOSEException e) {
            throw new RuntimeException("Error creating JWT token", e);
        }
    }

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);



        return signedJWT;
    }

    public record TokenInfo(String token, Date expiryDate) {}

    public record TokenPair(TokenInfo accessToken, TokenInfo refreshToken) {}
}
