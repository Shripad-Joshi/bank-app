package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.entity.User;
import com.bank_Project.bank_app.enums.AccountStatus;
import com.bank_Project.bank_app.enums.AccountType;
import com.bank_Project.bank_app.service.Impl.userServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private Long account_id;
    private Long userId;
    private String account_number;
    private double balance;
    private AccountStatus status;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public AccountDTO(){
        createdAt=LocalDateTime.now();
        updateAt=LocalDateTime.now();
    }

    public static Account prepareAccountEntity(AccountDTO accountDTO,UserDTO userDTO){
        Account account=new Account();
        account.setAccount_id(accountDTO.getAccount_id());
        account.setUser(UserDTO.prepareUserEntity(userDTO));
        account.setAccount_number(accountDTO.getAccount_number());
        account.setBalance(accountDTO.getBalance());
        account.setStatus(accountDTO.getStatus());
        account.setAccountType(accountDTO.getAccountType());
        account.setCreatedAt(accountDTO.getCreatedAt());
        account.setUpdateAt(accountDTO.getUpdateAt());
        return account;
    }
}
