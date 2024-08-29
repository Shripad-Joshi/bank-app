package com.bank_Project.bank_app.advice;

public class AccountInactiveException extends RuntimeException{
    public AccountInactiveException(String message){
        super(message);
    }
}
