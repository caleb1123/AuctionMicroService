package com.example.profileservice.service.impl;

import com.example.profileservice.dto.request.ProfileUserRequest;
import com.example.profileservice.dto.response.ProfileUserResponse;
import com.example.profileservice.entity.ProfileUser;
import com.example.profileservice.exception.AppException;
import com.example.profileservice.exception.ErrorCode;
import com.example.profileservice.mapper.ProfileUserMapper;
import com.example.profileservice.repository.ProfileUserRepository;
import com.example.profileservice.service.ProfileUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProfileServiceImpl implements ProfileUserService {
    ProfileUserRepository profileUserRepository;
    ProfileUserMapper profileUserMapper;
    @Override
    public ProfileUserResponse createProfileUser(ProfileUserRequest request) {
        if (profileUserRepository.findProfileUserByUserId(request.getUserId()).isPresent()) {
            throw new AppException(ErrorCode.PROFILE_USER_NOT_FOUND);
        }

        ProfileUser profileUser = profileUserMapper.toProfileUser(request);

        profileUserRepository.save(profileUser);

        return profileUserMapper.toProfileUserResponse(profileUser);

    }
}
