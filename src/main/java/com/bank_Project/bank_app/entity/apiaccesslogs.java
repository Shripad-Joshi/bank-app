package com.bank_Project.bank_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class apiaccesslogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    User user;*/

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private String httpMethod;

    @Column(nullable = false)
    private int statusCode;

    @Column(nullable = false)
    private String requestPayLoad;

    @Column(nullable = false)
    private String responsePayLoad;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private Long executionTime;

    @Column(nullable = false)
    private String userAgent;
}
