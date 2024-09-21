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
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Timestamp createdAt;
    Timestamp updatedAt;

    @ManyToOne
    User user;
    @ManyToOne
    Property property;
}
