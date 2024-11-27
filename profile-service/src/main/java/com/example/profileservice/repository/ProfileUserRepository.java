package com.example.profileservice.repository;

import com.example.profileservice.entity.ProfileUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileUserRepository extends Neo4jRepository<ProfileUser, String> {
    Optional<ProfileUser> findProfileUserByUserId(String userId);
}
