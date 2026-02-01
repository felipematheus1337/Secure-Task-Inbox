package com.taskinbox.v1.support.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            BusinessException ex, HttpServletRequest request
    ) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
