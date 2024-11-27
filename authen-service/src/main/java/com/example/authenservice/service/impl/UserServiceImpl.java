package com.example.authenservice.service.impl;

import com.example.authenservice.entity.User;
import com.example.authenservice.exception.AppException;
import com.example.authenservice.exception.ErrorCode;
import com.example.authenservice.mapper.UserMapper;
import com.example.authenservice.payload.request.SignUpRequest;
import com.example.authenservice.payload.response.SignUpResponse;
import com.example.authenservice.repository.RoleRepository;
import com.example.authenservice.repository.UserRepository;
import com.example.authenservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;  // Inject PasswordEncoder
    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        Optional<User> user = userRepository.findUserByUserName(request.getUserName());
        if(user.isPresent()) throw new AppException(ErrorCode.USER_EXISTED);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User userEntity = userMapper.toEntityfromEntity(request);
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        SignUpResponse response = new SignUpResponse();
        response.setMessage("User registered successfully");
        return response;
    }
}
