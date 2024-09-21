package com.laau.tranquilnest.entity;

import com.laau.tranquilnest.enums.BookingStatus;
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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Timestamp checkIn;
    Timestamp checkOut;
    BookingStatus status;
    Timestamp createdAt;
    Timestamp updatedAt;

    @ManyToOne
    User user;
    @ManyToMany
    List<Room> rooms;
    @ManyToMany
    List<Service> services;
    @OneToOne
    Payment payment;
}
