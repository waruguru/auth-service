package com.waruguru.auth_service.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@Component
public class TransactionIdFilter extends OncePerRequestFilter {
    public static final String TRANSACTION_ID = "X-Transaction-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String transactionId = UUID.randomUUID().toString();
        request.setAttribute(TRANSACTION_ID, transactionId);
        response.setHeader(TRANSACTION_ID, transactionId);

        filterChain.doFilter(request, response);
    }
}
