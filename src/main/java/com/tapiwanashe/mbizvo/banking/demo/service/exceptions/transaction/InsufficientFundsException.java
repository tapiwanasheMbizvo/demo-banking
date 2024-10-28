package com.tapiwanashe.mbizvo.banking.demo.service.exceptions.transaction;

public class InsufficientFundsException extends TransactionException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
