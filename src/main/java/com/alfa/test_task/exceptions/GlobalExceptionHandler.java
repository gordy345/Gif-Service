package com.alfa.test_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleExchangeRateNotFoundException(ExchangeRateNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity<>("Something went wrong, try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
