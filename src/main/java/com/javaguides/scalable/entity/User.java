package com.javaguides.scalable.entity;

import com.javaguides.scalable.entity.constants.GenderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderStatus gender;
    @Column(nullable = false)
    private String birthday;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
}
