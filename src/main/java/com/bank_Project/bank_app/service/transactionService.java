package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.TransactionDTO;

import java.util.List;

public interface transactionService {

    //deposit, withdrawl, transfer, get last 5 10  all transactions
    public TransactionDTO deposit(TransactionDTO transactionDTO);

    public TransactionDTO withdrawal(TransactionDTO transactionDTO);

    public TransactionDTO transfer(TransactionDTO transactionDTO);

    public List<TransactionDTO> last5Transactions(Long account_id);

    public List<TransactionDTO> last10Transactions(Long account_id);
}
