package com.andrade.bibliotecaApi.exceptions;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, exception.getLocalizedMessage(), null));
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NoResultException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, exception.getLocalizedMessage(), null));
    }
}
