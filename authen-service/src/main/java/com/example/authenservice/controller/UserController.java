package com.example.authenservice.controller;

import com.example.authenservice.payload.request.SignUpRequest;
import com.example.authenservice.payload.response.ApiResponse;
import com.example.authenservice.payload.response.SignUpResponse;
import com.example.authenservice.service.UserService;
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
public class UserController {
    UserService userService;

    @PostMapping("/sign-up")
    public ApiResponse<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        var result = userService.signUp(request);
        return ApiResponse.<SignUpResponse>builder().result(result).build();
    }
}
