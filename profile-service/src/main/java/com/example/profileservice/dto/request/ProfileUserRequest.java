package com.example.profileservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level =  lombok.AccessLevel.PRIVATE, makeFinal = false)
public class ProfileUserRequest {
    String userId;
    String Address;
    String Phone;
    String Avatar;
}
