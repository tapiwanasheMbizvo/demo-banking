package com.tapiwanashe.mbizvo.banking.demo.service;


import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.models.Transaction;
import com.tapiwanashe.mbizvo.banking.demo.models.enums.TransactionType;
import com.tapiwanashe.mbizvo.banking.demo.repositories.AccountTransactionRepository;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountTransactionRepoTests {


    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Test
    public  void testSavingTransaction(){
        String accountNumber = "AC1010";
        int initialBalance = 50000;


        BankAccount account = new BankAccount();
        account.setBalance(BigDecimal.valueOf(initialBalance));
        account.setAccountNumber(accountNumber);

        this.bankAccountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setTransactionDate(LocalDateTime.now());
        double val = 25.25;
        transaction.setAmount(BigDecimal.valueOf(val));

        this.accountTransactionRepository.save(transaction);

        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(val)));

        BankAccount bankAccount = this.bankAccountRepository.save(account);


        List<Transaction> saved = this.accountTransactionRepository.findAllByAccountNumber(accountNumber);

        Assertions.assertThat(saved.size()).isEqualTo(1);

        org.junit.jupiter.api.Assertions.assertEquals(saved.get(0).getAmount(), BigDecimal.valueOf(val));

        Assertions.assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(initialBalance).subtract(BigDecimal.valueOf(val)));

    }

    @Test
    @Disabled
    public  void  testCreditAccount(){

        BankAccount account = new BankAccount();
        int initialBalance = 50000;
        account.setBalance(BigDecimal.valueOf(initialBalance));
        String accountNumber = "ACACA25";
        account.setAccountNumber(accountNumber);

        bankAccountRepository.save(account);

        double val = 75.75;


        Optional<BankAccount> byAccountNumber = bankAccountRepository.findByAccountNumber(accountNumber);

        Assertions.assertThat(byAccountNumber.get().getBalance()).isEqualTo(BigDecimal.valueOf(initialBalance).add(BigDecimal.valueOf(val)));
    }

}
