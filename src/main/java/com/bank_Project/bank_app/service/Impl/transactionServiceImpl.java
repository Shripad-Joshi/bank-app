package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.DTO.TransactionDTO;
import com.bank_Project.bank_app.advice.AccountInactiveException;
import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.Transaction;
import com.bank_Project.bank_app.repository.transactionRepository;
import com.bank_Project.bank_app.service.transactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class transactionServiceImpl implements transactionService {

    @Autowired
    accountServiceImpl accountService;

    @Autowired
    transactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;
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
    public TransactionDTO withdrawal(TransactionDTO transactionDTO) {
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
            if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus().equals("Active") ||
                    accountService.getAccountById(transactionDTO.getTransfered_account_id()).getStatus().equals("Active")){
                Account debit= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()));
                Account credit= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()));
                double balance_debit=debit.getBalance();
                double balance_credit=credit.getBalance();
                debit.setBalance(balance_debit-transactionDTO.getAmount());
                debit.setBalance(balance_credit+transactionDTO.getAmount());
                //should check if balance available for transfer in debit and transaction entry should be added in both debit and credit
                AccountDTO accountDTO=accountService.updateAccount(debit.getAccount_id(),Account.prepareAccountDTO(debit));
                AccountDTO accountDTO1=accountService.updateAccount(credit.getAccount_id(), Account.prepareAccountDTO(credit));
                transactionDTO.setBalance_before(balance_debit);
                transactionDTO.setBalance_after(accountDTO.getBalance());
                transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO));
                transactionDTO.setBalance_before(balance_credit);
                transactionDTO.setBalance_after(accountDTO1.getBalance());
                transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO));
                return transactionDTO;
            }else{
                throw new AccountInactiveException("Account Inactive.");
            }
    }

   /* @Override
    public List<TransactionDTO> last5Transactions(Long account_id) {
        List<Transaction> transactionList=transactionRepository.findTop5ByAccountIdOrderByTransactionDateDesc(account_id);
        return transactionList.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> last10Transactions(Long account_id) {
        List<Transaction> transactionList=transactionRepository.findTop10ByAccountIdByTransactionDateDesc(account_id);
        return transactionList.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }*/
}
