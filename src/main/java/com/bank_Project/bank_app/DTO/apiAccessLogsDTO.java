package com.bank_Project.bank_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class apiAccessLogsDTO {
    private Long userId;
    private String endpoint;
    private String httpMethod;
    private int statusCode;
    private String requestPayload;
    private String responsePayload;
    private LocalDateTime timeStamp;
    private String userAgent;
    private Long executionTime;
}
