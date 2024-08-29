package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.Transaction;
import com.bank_Project.bank_app.enums.TransactionStatus;
import com.bank_Project.bank_app.enums.TransactionType;
import com.bank_Project.bank_app.service.Impl.accountServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @Autowired
    static accountServiceImpl accountService;

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


    public static Transaction prepareTransactionEntity(TransactionDTO transactionDTO){
        Transaction transaction=new Transaction();
        transaction.setId(transactionDTO.getTransaction_id());
        transaction.setAccount(AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId())));
        transaction.setTransfered_account(AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getTransfered_account_id())));
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
