package com.laau.tranquilnest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String name;
    String description;
    String address;
    Timestamp createdAt;
    Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @ManyToOne()
    Category category;
}
