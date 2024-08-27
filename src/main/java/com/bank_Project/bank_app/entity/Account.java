package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.enums.AccountStatus;
import com.bank_Project.bank_app.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName="userId")
    private User user;

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

    public static AccountDTO prepareAccountDTO(Account account){
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setAccount_id(account.getAccount_id());
        accountDTO.setAccount_number(account.getAccount_number());
        accountDTO.setUserId(account.getUser().getUserId());
        accountDTO.setBalance(accountDTO.getBalance());
        accountDTO.setStatus(accountDTO.getStatus());
        accountDTO.setAccountType(accountDTO.getAccountType());
        accountDTO.setCreatedAt(accountDTO.getCreatedAt());
        accountDTO.setUpdateAt(accountDTO.getUpdateAt());
        return accountDTO;
    }
}
