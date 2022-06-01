package com.alfa.test_task.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleExchangeRateNotFoundException(ExchangeRateNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        log.warn("Runtime exception happened, message: " + exception.getMessage());
        return new ResponseEntity<>("Something went wrong, try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
