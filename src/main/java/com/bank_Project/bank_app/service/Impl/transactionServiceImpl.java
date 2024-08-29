package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.DTO.TransactionDTO;
import com.bank_Project.bank_app.advice.AccountInactiveException;
import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.Transaction;
import com.bank_Project.bank_app.repository.transactionRepository;
import com.bank_Project.bank_app.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class transactionServiceImpl implements transactionService {

    @Autowired
    accountServiceImpl accountService;

    @Autowired
    transactionRepository transactionRepository;


        @Override
    public TransactionDTO deposit(TransactionDTO transactionDTO) {
        if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus().equals("Active")){
            Account account= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()));
            double balance=account.getBalance();
            account.setBalance(balance+transactionDTO.getAmount());
            AccountDTO accountDTO=accountService.updateAccount(account.getAccount_id(),Account.prepareAccountDTO(account));
            transactionDTO.setBalance_before(account.getBalance());
            transactionDTO.setBalance_after(accountDTO.getBalance());
            transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO));
            return transactionDTO;
        }else {
            //inactive
            throw new AccountInactiveException("Account Inactive.");
        }
    }

    @Override
    public TransactionDTO withdrawl(TransactionDTO transactionDTO) {
        if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus().equals("Active")){
            Account account= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()));
            double balance=account.getBalance();
            account.setBalance(balance-transactionDTO.getAmount());
            AccountDTO accountDTO=accountService.updateAccount(account.getAccount_id(),Account.prepareAccountDTO(account));
            transactionDTO.setBalance_before(account.getBalance());
            transactionDTO.setBalance_after(accountDTO.getBalance());
            transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO));
            return transactionDTO;
        }else {
            //inactive
            throw new AccountInactiveException("Account Inactive.");
        }
    }

    @Override
    public TransactionDTO transfer(TransactionDTO transactionDTO) {
        return null;
    }

    @Override
    public List<TransactionDTO> last5Transactions(Long account_id) {

        return null;
    }

    @Override
    public List<TransactionDTO> last10Transactions(Long account_id) {
        return null;
    }
}
