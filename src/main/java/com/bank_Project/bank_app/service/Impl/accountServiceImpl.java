package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.advice.ResourceNotFoundException;
import com.bank_Project.bank_app.entity.Account;
import com.bank_Project.bank_app.repository.accountRepository;
import com.bank_Project.bank_app.service.accountService;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class accountServiceImpl implements accountService {

    @Autowired
    accountRepository accountRepository;

    @Autowired
    userServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccountDTO insertAccount(AccountDTO accountDTO) {
        Account acc=AccountDTO.prepareAccountEntity(accountDTO);
        Account account=accountRepository.save(acc);
        return Account.prepareAccountDTO(account);
    }

    @Override
    public AccountDTO deleteAccount(Long id) {
        Account account=accountRepository.getById(id);
        accountRepository.deleteById(id);
        return Account.prepareAccountDTO(account);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> account=accountRepository.findById(id);
        if(account.isPresent()){
            return Account.prepareAccountDTO(account.get());
        }else{
            throw new ResourceNotFoundException("Account not found with ID: " + id);
        }
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account=accountRepository.getById(id);
        accountDTO.setAccount_id(account.getAccount_id());

        //only update not null values
        Condition<?, ?> condition = Conditions.isNotNull();
        modelMapper.getConfiguration().setPropertyCondition(condition);
        modelMapper.map(accountDTO,account);

        accountRepository.save(account);
        return Account.prepareAccountDTO(account);
    }

    @Override
    public List<AccountDTO> getAccountListByUserId(Long id) {
        List<Account> accountList=accountRepository.findAllAccountByUser(UserDTO.prepareUserEntity(userService.getUserById(id)));
        return accountList.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String checkAccount(Long id) {
        if(accountRepository.findById(id).isPresent())
            return "present";
        return null;
    }
}
