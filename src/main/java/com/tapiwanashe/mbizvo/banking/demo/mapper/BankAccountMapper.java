package com.tapiwanashe.mbizvo.banking.demo.mapper;

import com.tapiwanashe.mbizvo.banking.demo.dto.BankAccountDto;
import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {

    public BankAccount toEntity(BankAccountDto dto){

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(dto.getAccountNumber());
        bankAccount.setId(dto.getId());
        bankAccount.setBalance(dto.getBalance());
        return  bankAccount;
    }

    public BankAccountDto toDto(BankAccount account){


        return new BankAccountDto(account.getId(), account.getAccountNumber(), account.getBalance());
    }
}
