package com.bank_Project.bank_app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private int errorCode;
    private T data;
}
