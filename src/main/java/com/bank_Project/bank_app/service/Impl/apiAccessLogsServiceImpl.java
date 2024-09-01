package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.DTO.apiAccessLogsDTO;
import com.bank_Project.bank_app.entity.apiaccesslogs;
import com.bank_Project.bank_app.repository.apiAccessLogsRepository;
import com.bank_Project.bank_app.service.apiAccessLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class apiAccessLogsServiceImpl implements apiAccessLogsService {

    @Autowired
    apiAccessLogsRepository apiAccessLogsRepository;

    @Autowired
    userServiceImpl userService;

    @Override
    public void saveApiAccessLog(apiAccessLogsDTO apiAccessLogsDTO) {
        apiaccesslogs log=new apiaccesslogs();
        //log.setUser(UserDTO.prepareUserEntity(userService.getUserById(apiAccessLogsDTO.getUserId())));
        log.setEndpoint(apiAccessLogsDTO.getEndpoint());
        log.setHttpMethod(apiAccessLogsDTO.getHttpMethod());
        log.setStatusCode(apiAccessLogsDTO.getStatusCode());
        log.setRequestPayLoad(apiAccessLogsDTO.getRequestPayload());
        log.setResponsePayLoad(apiAccessLogsDTO.getResponsePayload());
        log.setTimeStamp(apiAccessLogsDTO.getTimeStamp());
        log.setExecutionTime(apiAccessLogsDTO.getExecutionTime());
        log.setUserAgent(apiAccessLogsDTO.getUserAgent());
        apiAccessLogsRepository.save(log);
    }
}
