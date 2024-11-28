package com.example.authenservice.payload.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    String userName;
    String password;
    String firstName;
    String lastName;
    String address;
    String phone;
    String avatar;
}
