package com.example.profileservice.controller;

import com.example.profileservice.dto.request.ProfileUserRequest;
import com.example.profileservice.dto.response.ApiResponse;
import com.example.profileservice.dto.response.ProfileUserResponse;
import com.example.profileservice.service.ProfileUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileUserController {
    ProfileUserService profileUserService;

    @PostMapping("/create")
    public ApiResponse<ProfileUserResponse> createProfileUser(@RequestBody ProfileUserRequest request) {
        var result = profileUserService.createProfileUser(request);
        return ApiResponse.<ProfileUserResponse>builder()
                .result(result)
                .build();
    }
}
