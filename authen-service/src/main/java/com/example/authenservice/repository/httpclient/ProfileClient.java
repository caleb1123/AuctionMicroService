package com.example.authenservice.repository.httpclient;

import com.example.authenservice.payload.request.ProfileUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "http://localhost:8081/profile")
public interface ProfileClient {
    @PostMapping(value = "/user/create", produces = MediaType.APPLICATION_JSON_VALUE)
    Object createProfileUser(@RequestBody ProfileUserRequest request);
}
