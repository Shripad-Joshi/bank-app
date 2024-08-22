package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.enums.AccountStatus;
import com.bank_Project.bank_app.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private int account_id;
    private int user_id;
    private String account_number;
    private double balance;
    private AccountStatus status;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public static Account prepareAccountDTO(AccountDTO accountDTO){
        Account account=new Account();
        account.setAccount_id(accountDTO.getAccount_id());
        account.setUserid(accountDTO.getUser_id());
        account.setAccount_number(accountDTO.getAccount_number());
        account.setBalance(accountDTO.getBalance());
        account.setStatus(accountDTO.getStatus());
        account.setAccountType(accountDTO.getAccountType());
        return account;
    }
}
