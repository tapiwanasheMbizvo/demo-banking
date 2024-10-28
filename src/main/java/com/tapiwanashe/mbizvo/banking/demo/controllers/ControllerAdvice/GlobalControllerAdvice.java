package com.tapiwanashe.mbizvo.banking.demo.controllers.ControllerAdvice;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<TransactionError> handleException(RuntimeException exception){

        TransactionError transactionError = new TransactionError();
        transactionError.errorMessage = exception.getMessage();
        transactionError.responseStatus = HttpStatus.BAD_REQUEST;

        return  ResponseEntity.ok(transactionError);
    }
}
