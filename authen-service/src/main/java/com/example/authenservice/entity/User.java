package com.example.authenservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(unique = true)
    String userName;

    @Column
    String password;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    boolean isActive;

    @ManyToOne
    @JoinColumn(name = "roleId")
    Role role;
}
