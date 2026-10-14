package com.andrade.bibliotecaApi.exceptions;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Collectors;

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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleParametroAusente(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, "Parâmetro " + ex.getParameterName() + " é obrigatório", null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        String mensagem = ex.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, mensagem, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, mensagem, null));
    }
}
