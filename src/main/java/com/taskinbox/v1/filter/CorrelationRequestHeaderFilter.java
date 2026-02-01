package com.taskinbox.v1.filter;

import com.taskinbox.v1.utils.TaskUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


public class CorrelationRequestHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String correlationId = request.getHeader(TaskUtils.X_HEADER);

        if (correlationId == null || correlationId.isBlank())  {

            correlationId = UUID.randomUUID().toString();

        }

        MDC.put("correlationId", correlationId);
        response.setHeader(TaskUtils.X_HEADER, correlationId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }

    }
}
