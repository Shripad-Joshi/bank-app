package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.enums.AccountStatus;
import com.bank_Project.bank_app.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    private int account_id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private int userid;

    @Column(nullable = false,unique = true)
    private String account_number;

    @Column(nullable = false)
    private double balance=0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(name="created_At",updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_At")
    private LocalDateTime updateAt;


}
