package com.bank_Project.bank_app.repository;

import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface accountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllAccountByUser(User user);
}
