package com.example.authenservice.mapper;

import com.example.authenservice.entity.User;
import com.example.authenservice.payload.request.SignUpRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntityfromEntity(SignUpRequest request);
}
