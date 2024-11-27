package com.example.profileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Node("ProfileUser")
public class ProfileUser {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;  // Đây là ID Neo4j tự động tạo

    @Property("userId")
    String userId;

    @Property("Address")
    String Address;

    @Property("Phone")
    String Phone;

    @Property("Avatar")
    String Avatar;

}
