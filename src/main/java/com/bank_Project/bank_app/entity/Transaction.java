package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.DTO.TransactionDTO;
import com.bank_Project.bank_app.enums.TransactionStatus;
import com.bank_Project.bank_app.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account accountId;

    @ManyToOne
    @JoinColumn(name="account_id",insertable = false, updatable = false, nullable = true)
    private Account transfered_account_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus;

    private String description;

    @Column(nullable = false)
    private double balance_before;

    @Column(nullable = false)
    private double balance_after;

    public static TransactionDTO prepareTransactionDTO(Transaction transaction){
        TransactionDTO transactionDTO=new TransactionDTO();
        transactionDTO.setTransaction_id(transaction.getTransaction_id());
        transactionDTO.setAccountId(transaction.getAccountId().getAccount_id());
        transactionDTO.setTransfered_account_id(transaction.getTransfered_account_id().getAccount_id());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        transactionDTO.setTransactionStatus(transaction.getTransactionStatus());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setBalance_before(transaction.getBalance_before());
        transactionDTO.setBalance_after(transaction.getBalance_after());
        return transactionDTO;
    }
}
