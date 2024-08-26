package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.AccountDTO;

import java.util.List;

public interface accountService {
    public AccountDTO insertAccount(AccountDTO accountDTO);
    public AccountDTO deleteAccount(Long id);
    public AccountDTO getAccountById(Long id);
    public  AccountDTO updateAccount(Long id,AccountDTO accountDTO);
    public List<AccountDTO> getAccountListByUserId(Long id);
    public String checkAccount(Long id);
}
