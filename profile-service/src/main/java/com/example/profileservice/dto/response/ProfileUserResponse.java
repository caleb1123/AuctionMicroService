package com.example.profileservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level =  lombok.AccessLevel.PRIVATE, makeFinal = false)
public class ProfileUserResponse {
    String id;
    String userId;
    String Address;
    String Phone;
    String Avatar;
}
