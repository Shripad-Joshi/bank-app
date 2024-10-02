package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.Transaction;
import com.bank_Project.bank_app.enums.TransactionStatus;
import com.bank_Project.bank_app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {

    private Long transaction_id;
    private Long accountId;
    private Long transfered_account_id;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private TransactionStatus transactionStatus;
    private String description;
    private double balance_before;
    private double balance_after;

    public TransactionDTO(){
        transactionDate=LocalDateTime.now();
    }

    public static Transaction prepareTransactionEntity(TransactionDTO transactionDTO,UserDTO userDTO,AccountDTO accountDTO){
        Transaction transaction=new Transaction();
        transaction.setTransaction_id(transactionDTO.getTransaction_id());
        transaction.setAccountId(AccountDTO.prepareAccountEntity(accountDTO,userDTO));
        transaction.setTransfered_account_id(AccountDTO.prepareAccountEntity(accountDTO,userDTO));
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setTransactionStatus(transactionDTO.getTransactionStatus());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setBalance_before(transactionDTO.getBalance_before());
        transaction.setBalance_after(transaction.getBalance_after());
        return transaction;
    }
}
