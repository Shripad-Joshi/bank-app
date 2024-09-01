package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.apiAccessLogsDTO;
import org.springframework.stereotype.Service;

@Service
public interface apiAccessLogsService {

    public void saveApiAccessLog(apiAccessLogsDTO apiAccessLogsDTO);
}
