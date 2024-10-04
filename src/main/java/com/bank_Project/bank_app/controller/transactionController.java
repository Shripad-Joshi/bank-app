package com.bank_Project.bank_app.controller;

import com.bank_Project.bank_app.ApiResponse;
import com.bank_Project.bank_app.DTO.TransactionDTO;
import com.bank_Project.bank_app.service.Impl.transactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class transactionController {
    @Autowired
    transactionServiceImpl transactionService;

    @PostMapping(path="/withdrawal",consumes="application/json",produces="application/json")
    public ResponseEntity<ApiResponse<TransactionDTO>> withdrawl(@RequestBody TransactionDTO transactionDTO){
        TransactionDTO transactionDTO1=transactionService.withdrawal(transactionDTO);
        ApiResponse apiResponse=new ApiResponse<>("Withdrawal complete.",200,transactionDTO1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(path="/deposit",consumes="application/json",produces="application/json")
    public ResponseEntity<ApiResponse<TransactionDTO>> deposit(@RequestBody TransactionDTO transactionDTO){
        TransactionDTO transactionDTO1=transactionService.deposit(transactionDTO);
        ApiResponse apiResponse=new ApiResponse<>("Deposit complete.",200,transactionDTO1);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(produces="application/json",path="/getlast5transaction")
    public ResponseEntity<ApiResponse<TransactionDTO>> last5Transaction(@RequestParam Long account_id){
        List<TransactionDTO> transactionDTOList=transactionService.last5Transactions(account_id);
        if(transactionDTOList.isEmpty()!=true) {
            ApiResponse apiResponse = new ApiResponse<>("Last 5 transaction retrieved complete.", 200, transactionDTOList);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }else{
            ApiResponse apiResponse=new ApiResponse<>("No transaction found.",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces="application/json",path="/getlast10transaction")
    public ResponseEntity<ApiResponse<TransactionDTO>> last10Transaction(@RequestParam Long account_id){
        List<TransactionDTO> transactionDTOList=transactionService.last10Transactions(account_id);
        if(transactionDTOList.isEmpty()!=true) {
            ApiResponse apiResponse = new ApiResponse<>("Last 10 transaction retrieved complete.", 200, transactionDTOList);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }else{
            ApiResponse apiResponse=new ApiResponse<>("No transaction found.",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }

    }

   /* @PostMapping(produces = "application/json",consumes="application/json",path="/transfer")
    public ResponseEntity<ApiResponse<TransactionDTO>> transfer(@RequestBody TransactionDTO transactionDTO){
        TransactionDTO transactionDTO1=transactionService.transfer(transactionDTO);
        ApiResponse apiResponse=new ApiResponse<>("Transfer complete.",200,transactionDTO1);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }*/
}
