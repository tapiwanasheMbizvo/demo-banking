package com.tapiwanashe.mbizvo.banking.demo.service;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountTransactionServiceTest {


    @Mock
    private BankAccountRepository accountRepository;

    @InjectMocks
    private  AccountTransactionService transactionService;
    String accountNumber = "AC100001";
    BigDecimal amountDeduct = BigDecimal.valueOf(50);


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deductFromAccount(){


        BankAccount bankAccount = new BankAccount(

                13L,
                accountNumber,
                BigDecimal.valueOf(300)
        );

        BankAccount saved = new BankAccount(

                13L,
                accountNumber,
                BigDecimal.valueOf(1000)
        );


        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(bankAccount));
        when(accountRepository.save(any())).thenReturn(saved);


          transactionService.debitFromAccount(accountNumber, amountDeduct);

       // assertTrue(deductFromAccount);


    }

}