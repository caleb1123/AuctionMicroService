package com.example.profileservice.service;

import com.example.profileservice.dto.request.ProfileUserRequest;
import com.example.profileservice.dto.response.ProfileUserResponse;

public interface ProfileUserService {
    ProfileUserResponse createProfileUser(ProfileUserRequest request);
}
