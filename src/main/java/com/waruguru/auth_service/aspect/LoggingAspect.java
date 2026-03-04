package com.waruguru.auth_service.aspect;

import com.waruguru.auth_service.filter.TransactionIdFilter;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Around("execution(* com.waruguru.auth_service.controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String transactionId = (String) request.getAttribute(TransactionIdFilter.TRANSACTION_ID);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            // Log exception in your format
            logger.error("date={} | transactionId={} | message=Failed | error={} | responseBody={}",
                    LocalDate.now(),
                    transactionId,
                    ex.getMessage(),
                    "N/A");
            throw ex;
        }

        // Log success in your format
        logger.info("date={} | transactionId={} | message=Success | responseBody={}",
                LocalDate.now(),
                transactionId,
                result);

        return result;
    }
}

