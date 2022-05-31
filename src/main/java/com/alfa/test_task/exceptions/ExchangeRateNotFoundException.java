package com.alfa.test_task.exceptions;

public class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String message) {
        super(message);
    }
}
