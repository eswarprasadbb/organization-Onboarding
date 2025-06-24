package com.example.organizationservice.filter;

import com.example.organizationservice.service.ApiCallLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiCallLoggingFilter extends OncePerRequestFilter {

    private final ApiCallLogService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            UUID userId = null; // fetch from security context if available
            UUID orgId = null;  // fetch from security context if available
            service.record(userId, orgId,
                    request.getMethod(), request.getRequestURI(), response.getStatus(), duration, request.getRemoteAddr());
        }
    }
}
