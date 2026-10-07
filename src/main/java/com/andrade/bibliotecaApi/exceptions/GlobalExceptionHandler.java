package com.andrade.bibliotecaApi.exceptions;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, exception.getLocalizedMessage(), null));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(RecursoNaoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, exception.getLocalizedMessage(), null));
    }

    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnprocessable(UnprocessableException exception){
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiResponse<>(false, exception.getLocalizedMessage(), null));
    }
}
