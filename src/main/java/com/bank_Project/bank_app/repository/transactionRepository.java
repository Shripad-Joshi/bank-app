package com.bank_Project.bank_app.repository;

import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface transactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTop5ByAccountIdOrderByTransactionDateDesc(Account account);

    List<Transaction> findTop10ByAccountIdOrderByTransactionDateDesc(Account account);
}
