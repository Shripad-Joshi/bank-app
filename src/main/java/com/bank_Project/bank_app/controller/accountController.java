package com.bank_Project.bank_app.controller;

import com.bank_Project.bank_app.ApiResponse;
import com.bank_Project.bank_app.DTO.AccountDTO;
import com.bank_Project.bank_app.service.Impl.accountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class accountController {

    @Autowired
    accountServiceImpl accountService;


    @PostMapping(consumes = "application/json",path="/addAccount")
    public ResponseEntity<ApiResponse<AccountDTO>> insertAccount(@RequestBody AccountDTO accountDTO){
        AccountDTO accountDTO1=accountService.insertAccount(accountDTO);
        ApiResponse<AccountDTO> apiResponse=new ApiResponse<>("Account created successfully.",200,accountDTO1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json",path="/getAccount")
    public ResponseEntity<ApiResponse<AccountDTO>> getAccount(@RequestParam Long id){
        AccountDTO accountDTO=accountService.getAccountById(id);
        ApiResponse<AccountDTO> apiResponse;
        if(accountDTO!=null){
            apiResponse=new ApiResponse<>("Account found successfully.",200,accountDTO);
        }else{
            apiResponse=new ApiResponse<>("Account not found.",404,null);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping(produces="application/json",path = "/deleteAccount")
    public ResponseEntity<ApiResponse<AccountDTO>> deleteAccount(@RequestParam Long id){
        ApiResponse apiResponse;
        if(accountService.checkAccount(id)!=null){
            AccountDTO accountDTO=accountService.getAccountById(id);
            apiResponse=new ApiResponse("Account delete successfully",200,accountDTO);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }else{
            apiResponse=new ApiResponse<>("Account not found with "+id+".",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes="application/json",produces="application/json",path="/updateAccount")
    public ResponseEntity<ApiResponse<AccountDTO>> updateAccount(@RequestParam Long id,@RequestBody AccountDTO accountDTO){
        ApiResponse apiResponse;
        if(accountService.checkAccount(id)!=null){
            AccountDTO accountDTO1=accountService.updateAccount(id, accountDTO);
            apiResponse=new ApiResponse<>("Account updated successfully.",200,accountDTO1);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }else{
            apiResponse=new ApiResponse<>("Account not found.",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json",path="/getAccountByUserId")
    public ResponseEntity<ApiResponse<AccountDTO>> getAllAccount(@RequestParam Long id){
        List<AccountDTO> accountDTOList=accountService.getAccountListByUserId(id);
        if(accountDTOList.isEmpty()!=true) {
            ApiResponse apiResponse = new ApiResponse<>("Account linked to " + id + "(user id) found successfully.", 200, accountDTOList);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse apiResponse=new ApiResponse<>("No account linked to "+id+"(user id).",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }
}
