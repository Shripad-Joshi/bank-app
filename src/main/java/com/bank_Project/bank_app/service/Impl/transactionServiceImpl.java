package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.DTO.TransactionDTO;
import com.bank_Project.bank_app.advice.AccountInactiveException;
import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.Transaction;
import com.bank_Project.bank_app.enums.AccountStatus;
import com.bank_Project.bank_app.enums.TransactionStatus;
import com.bank_Project.bank_app.repository.transactionRepository;
import com.bank_Project.bank_app.service.transactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class transactionServiceImpl implements transactionService {

    @Autowired
    accountServiceImpl accountService;

    @Autowired
    userServiceImpl userService;

    @Autowired
    transactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

        @Override
    public TransactionDTO deposit(TransactionDTO transactionDTO) {
        if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus()== AccountStatus.Active){
            Account account= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()),
                    userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()));
            double balance=account.getBalance();
            transactionDTO.setBalance_before(account.getBalance());
            account.setBalance(balance+transactionDTO.getAmount());
            AccountDTO accountDTO=accountService.updateAccount(account.getAccount_id(),Account.prepareAccountDTO(account));
            transactionDTO.setBalance_after(accountDTO.getBalance());
            transactionDTO.setTransactionStatus(TransactionStatus.Completed);
            transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO,
                    userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()),
                    accountService.getAccountById(transactionDTO.getAccountId())));
            return transactionDTO;
        }else {
            //inactive
            throw new AccountInactiveException("Account Inactive.");
        }
    }


    @Override
    public TransactionDTO withdrawal(TransactionDTO transactionDTO) {
        if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus()== AccountStatus.Active){
            Account account= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()),
                    userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()));
            double balance=account.getBalance();
            transactionDTO.setBalance_before(account.getBalance());
            account.setBalance(balance-transactionDTO.getAmount());
            AccountDTO accountDTO=accountService.updateAccount(account.getAccount_id(),Account.prepareAccountDTO(account));
            transactionDTO.setBalance_after(accountDTO.getBalance());
            transactionDTO.setTransactionStatus(TransactionStatus.Completed);
            transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO,
                    userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()),
                    accountService.getAccountById(transactionDTO.getAccountId())));
            return transactionDTO;
        }else {
            //inactive
            throw new AccountInactiveException("Account Inactive.");
        }
    }

    @Override
    public TransactionDTO transfer(TransactionDTO transactionDTO) {
            
            if(accountService.getAccountById(transactionDTO.getAccountId()).getStatus()== AccountStatus.Active ||
                    accountService.getAccountById(transactionDTO.getTransfered_account_id()).getStatus()== AccountStatus.Active){
                Account debit= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getAccountId()),
                        userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()));

                Account credit= AccountDTO.prepareAccountEntity(accountService.getAccountById(transactionDTO.getTransfered_account_id()),
                        userService.getUserById(accountService.getAccountById(transactionDTO.getTransfered_account_id()).getUserId()));

                double balance_debit=debit.getBalance();
                double balance_credit=credit.getBalance();
                /*transactionDTO.setBalance_before(balance_debit);
                transactionDTO.setBalance_before(balance_credit);
                */debit.setBalance(balance_debit-transactionDTO.getAmount());
                credit.setBalance(balance_credit+transactionDTO.getAmount());
                //should check if balance available for transfer in debit and transaction entry should be added in both debit and credit
                AccountDTO accountDTO=accountService.updateAccount(debit.getAccount_id(),Account.prepareAccountDTO(debit));
                AccountDTO accountDTO1=accountService.updateAccount(credit.getAccount_id(), Account.prepareAccountDTO(credit));
                transactionDTO.setBalance_after(accountDTO.getBalance());
                transactionDTO.setTransactionStatus(TransactionStatus.Completed);
                transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO,
                        userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()),
                        accountService.getAccountById(transactionDTO.getAccountId())));
                transactionDTO.setBalance_after(accountDTO1.getBalance());
                transactionDTO.setTransactionStatus(TransactionStatus.Completed);
                transactionRepository.save(TransactionDTO.prepareTransactionEntity(transactionDTO,
                        userService.getUserById(accountService.getAccountById(transactionDTO.getAccountId()).getUserId()),
                        accountService.getAccountById(transactionDTO.getAccountId())));
                return transactionDTO;
            }else{
                throw new AccountInactiveException("Account Inactive.");
            }
    }

    @Override
    public List<TransactionDTO> last5Transactions(Long account_id) {
        List<Transaction> transactionList=transactionRepository.findTop5ByAccountIdOrderByTransactionDateDesc(
                AccountDTO.prepareAccountEntity(accountService.getAccountById(account_id),
                        userService.getUserById(accountService.getAccountById(account_id).getUserId())));
        return transactionList.stream()
                .map(transaction -> {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setTransaction_id(transaction.getTransaction_id());
                    transactionDTO.setAccountId(transaction.getAccountId().getAccount_id());
                    if (transaction.getTransfered_account_id() != null) {
                        transactionDTO.setTransfered_account_id(transaction.getTransfered_account_id().getAccount_id());
                    }
                    transactionDTO.setTransactionType(transaction.getTransactionType());
                    transactionDTO.setAmount(transaction.getAmount());
                    transactionDTO.setTransactionDate(transaction.getTransactionDate());
                    transactionDTO.setTransactionStatus(transaction.getTransactionStatus());
                    transactionDTO.setDescription(transaction.getDescription());
                    transactionDTO.setBalance_before(transaction.getBalance_before());
                    transactionDTO.setBalance_after(transaction.getBalance_after());
                    return transactionDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> last10Transactions(Long account_id) {
        List<Transaction> transactionList=transactionRepository.findTop10ByAccountIdOrderByTransactionDateDesc(
                AccountDTO.prepareAccountEntity(accountService.getAccountById(account_id),
                        userService.getUserById(accountService.getAccountById(account_id).getUserId())));
        return transactionList.stream()
                .map(transaction -> {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setTransaction_id(transaction.getTransaction_id());
                    transactionDTO.setAccountId(transaction.getAccountId().getAccount_id());
                    if (transaction.getTransfered_account_id() != null) {
                        transactionDTO.setTransfered_account_id(transaction.getTransfered_account_id().getAccount_id());
                    }
                    transactionDTO.setTransactionType(transaction.getTransactionType());
                    transactionDTO.setAmount(transaction.getAmount());
                    transactionDTO.setTransactionDate(transaction.getTransactionDate());
                    transactionDTO.setTransactionStatus(transaction.getTransactionStatus());
                    transactionDTO.setDescription(transaction.getDescription());
                    transactionDTO.setBalance_before(transaction.getBalance_before());
                    transactionDTO.setBalance_after(transaction.getBalance_after());
                    return transactionDTO;
                })
                .collect(Collectors.toList());
    }
}
