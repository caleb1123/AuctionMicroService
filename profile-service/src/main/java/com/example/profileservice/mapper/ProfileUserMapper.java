package com.example.profileservice.mapper;

import com.example.profileservice.dto.request.ProfileUserRequest;
import com.example.profileservice.dto.response.ProfileUserResponse;
import com.example.profileservice.entity.ProfileUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileUserMapper {
    ProfileUser toProfileUser(ProfileUserRequest request);

    ProfileUserResponse toProfileUserResponse(ProfileUser profileUser);
}
