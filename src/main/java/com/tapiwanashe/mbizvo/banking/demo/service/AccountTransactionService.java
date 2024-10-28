package com.tapiwanashe.mbizvo.banking.demo.service;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.models.Transaction;
import com.tapiwanashe.mbizvo.banking.demo.models.enums.TransactionType;
import com.tapiwanashe.mbizvo.banking.demo.repositories.AccountTransactionRepository;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import com.tapiwanashe.mbizvo.banking.demo.service.exceptions.account.AccountNotFoundException;
import com.tapiwanashe.mbizvo.banking.demo.service.exceptions.transaction.InsufficientFundsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountTransactionService {

    private final BankAccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionService(BankAccountRepository accountRepository,
                                     AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Transactional
    public boolean transferBetweenTwoAccounts(String sourceAccount,
                                              String sinkAccount,
                                              BigDecimal amount) {
        debitFromAccount(sourceAccount, amount);
        creditToAccount(sinkAccount, amount);
        recordTransactions(sourceAccount, sinkAccount, amount);
        return true;
    }



    public void debitFromAccount(String accountNumber, BigDecimal amountDeduct) {
        var bankAccount = findAccountByNumber(accountNumber);

        if (bankAccount.getBalance().compareTo(amountDeduct) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account: " + accountNumber);
        }

        bankAccount.setBalance(bankAccount.getBalance().subtract(amountDeduct));
        accountRepository.save(bankAccount);
    }


    public void creditToAccount(String accountNumber, BigDecimal amountToAdd) {
        var bankAccount = findAccountByNumber(accountNumber);
        bankAccount.setBalance(bankAccount.getBalance().add(amountToAdd));
        accountRepository.save(bankAccount);

    }

    private BankAccount findAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account number " + accountNumber + " not found"));
    }

    private void recordTransactions(String sourceAccount, String sinkAccount, BigDecimal amount) {
        var debitTransactionRecord = new Transaction();
        var creditTransactionRecord = new Transaction();

        var transactionTime = LocalDateTime.now();

        debitTransactionRecord.setTransactionDate(transactionTime);
        debitTransactionRecord.setAmount(amount);
        debitTransactionRecord.setTransactionType(TransactionType.DEBIT);
        debitTransactionRecord.setAccountNumber(sourceAccount);


        creditTransactionRecord.setTransactionDate(transactionTime);
        creditTransactionRecord.setAmount(amount);
        creditTransactionRecord.setTransactionType(TransactionType.CREDIT);
        creditTransactionRecord.setAccountNumber(sinkAccount);

        accountTransactionRepository.save(debitTransactionRecord);
        accountTransactionRepository.save(creditTransactionRecord);
    }

}
