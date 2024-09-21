package com.laau.tranquilnest.entity;

import com.laau.tranquilnest.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique=true, nullable=false)
    String username;
    @Column(nullable=false)
    String password;
    String fullName;
    @Column(unique=true)
    String email;
    String phone;
    Role role;
    Timestamp createdAt;
    Timestamp updatedAt;
}
