package com.example.authenservice.repository;

import com.example.authenservice.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String token);
    Optional<RefreshToken> findRefreshTokenByUserName(String userName);
}
