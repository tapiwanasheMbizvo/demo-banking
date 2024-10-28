package com.tapiwanashe.mbizvo.banking.demo.controllers;


import com.tapiwanashe.mbizvo.banking.demo.service.AccountTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/transaction-service")
public class AccountTransactionController {


    private final AccountTransactionService accountTransactionService;

    public AccountTransactionController(AccountTransactionService accountTransactionService) {
        this.accountTransactionService = accountTransactionService;
    }

    @GetMapping
    public ResponseEntity<Boolean> doTransfer(@RequestParam("sourceAccount") String sourceAccount,
                                              @RequestParam("sinkAccount") String sinkAccount,
                                              @RequestParam("amount")BigDecimal amount){

        boolean b = accountTransactionService.transferBetweenTwoAccounts(sourceAccount, sinkAccount, amount);

        return  ResponseEntity.ok(b);
    }
}
