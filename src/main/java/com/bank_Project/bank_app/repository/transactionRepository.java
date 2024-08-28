package com.bank_Project.bank_app.repository;

import com.bank_Project.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transactionRepository extends JpaRepository<Transaction,Long> {
}
