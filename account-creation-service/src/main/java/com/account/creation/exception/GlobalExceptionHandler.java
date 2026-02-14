package com.account.creation.exception;

import com.account.creation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorResponse error = new ErrorResponse(
                400,
                errorMessage
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
