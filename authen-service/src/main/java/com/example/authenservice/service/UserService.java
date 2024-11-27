package com.example.authenservice.service;

import com.example.authenservice.payload.request.SignUpRequest;
import com.example.authenservice.payload.response.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
}
