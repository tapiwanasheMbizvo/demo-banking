package com.tapiwanashe.mbizvo.banking.demo.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tapiwanashe.mbizvo.banking.demo.dto.BankAccountDto;
import com.tapiwanashe.mbizvo.banking.demo.mapper.BankAccountMapper;
import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @InjectMocks
    private BankAccountService bankAccountService;

    private BankAccount fromAccount;
    private BankAccount toAccount;
    private BankAccountDto fromAccountDto;
    private BankAccountDto toAccountDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Set up dummy accounts
        fromAccount = new BankAccount();
        fromAccount.setAccountNumber("12345");
        fromAccount.setBalance(BigDecimal.valueOf(500));

        toAccount = new BankAccount();
        toAccount.setAccountNumber("67890");
        toAccount.setBalance(BigDecimal.valueOf(300));

        // Set up the corresponding DTOs
        fromAccountDto = new BankAccountDto();
        toAccountDto = new BankAccountDto();
    }

    @Test
    public void testTransferBetweenAccounts_Success() {
        BigDecimal transferAmount = BigDecimal.valueOf(100);

        // Mock repository behavior
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(Optional.of(fromAccount));
        when(bankAccountRepository.findByAccountNumber("67890")).thenReturn(Optional.of(toAccount));

        // Mock mapping behavior
        when(bankAccountMapper.toDto(fromAccount)).thenReturn(fromAccountDto);
        when(bankAccountMapper.toDto(toAccount)).thenReturn(toAccountDto);

        // Perform the transfer
        List<BankAccount> result = bankAccountService.transferBetweenAccounts("12345", "67890", transferAmount);

        // Verify balances updated
        assertEquals(BigDecimal.valueOf(400), fromAccount.getBalance());
        assertEquals(BigDecimal.valueOf(400), toAccount.getBalance());

        // Verify result contains both updated accounts
        assertEquals(2, result.size());
      //  assertTrue(result.contains(fromAccountDto));
       // assertTrue(result.contains(toAccountDto));

        // Verify interactions with repository and mapper
        verify(bankAccountRepository, times(1)).findByAccountNumber("12345");
        verify(bankAccountRepository, times(1)).findByAccountNumber("67890");
      //  verify(bankAccountMapper, times(2)).toDto(any(BankAccount.class));
    }

    @Test
    public void testTransferBetweenAccounts_InsufficientBalance() {
        BigDecimal transferAmount = BigDecimal.valueOf(600); // More than balance

        // Mock repository behavior
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(Optional.of(fromAccount));
        when(bankAccountRepository.findByAccountNumber("67890")).thenReturn(Optional.of(toAccount));

        // Assert exception for insufficient balance
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountService.transferBetweenAccounts("12345", "67890", transferAmount);
        });

        assertEquals("Insufficient balance in the from account.", exception.getMessage());

        // Verify no transfer happened
        assertEquals(BigDecimal.valueOf(500), fromAccount.getBalance());
        assertEquals(BigDecimal.valueOf(300), toAccount.getBalance());
    }

    @Test
    public void testTransferBetweenAccounts_SameAccount() {
        BigDecimal transferAmount = BigDecimal.valueOf(100);

        // Assert exception when transferring to the same account
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountService.transferBetweenAccounts("12345", "12345", transferAmount);
        });

        assertEquals("from and to account cannot be the same ", exception.getMessage());
    }

    @Test
    public void testTransferBetweenAccounts_AccountNotFound() {
        BigDecimal transferAmount = BigDecimal.valueOf(100);

        // Mock repository behavior for missing account
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(Optional.of(fromAccount));
        when(bankAccountRepository.findByAccountNumber("67890")).thenReturn(Optional.empty());

        // Assert exception for non-existent account
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountService.transferBetweenAccounts("12345", "67890", transferAmount);
        });

        assertEquals("Verify that both accounts are correct ", exception.getMessage());
    }
}
