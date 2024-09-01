package com.bank_Project.bank_app.aspects;

import com.bank_Project.bank_app.DTO.apiAccessLogsDTO;
import com.bank_Project.bank_app.entity.User;
import com.bank_Project.bank_app.service.Impl.apiAccessLogsServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class apiAccessAspect {

    @Autowired
    private apiAccessLogsServiceImpl apiAccessLogsService;

    @Around("execution(* com.bank_Project.bank_app.controller..*(..))")
    public Object logApiAccess(ProceedingJoinPoint joinPoint) throws Throwable{
        apiAccessLogsDTO apiAccessLogsDTO=new apiAccessLogsDTO();
        apiAccessLogsDTO.setEndpoint(joinPoint.getSignature().toShortString());
        apiAccessLogsDTO.setHttpMethod("method");
        apiAccessLogsDTO.setTimeStamp(LocalDateTime.now());
        apiAccessLogsDTO.setUserAgent("user-agent");

        Object[] args=joinPoint.getArgs();
        String requestPayLoad= Arrays.toString(args);
        apiAccessLogsDTO.setRequestPayload(requestPayLoad);

        //User user=new User();
        long startTime=System.currentTimeMillis();

        Object result=null;
        try{
            result=joinPoint.proceed();
            apiAccessLogsDTO.setStatusCode(200);
            apiAccessLogsDTO.setResponsePayload(result != null ? result.toString() : null);
        }catch (Exception exception){
            apiAccessLogsDTO.setStatusCode(500);
            apiAccessLogsDTO.setResponsePayload(exception.getMessage());
            throw exception;
        }finally {
            long excutionTime=System.currentTimeMillis()-startTime;
            apiAccessLogsDTO.setExecutionTime(excutionTime);
            apiAccessLogsService.saveApiAccessLog(apiAccessLogsDTO);
        }
        return result;
    }
}
