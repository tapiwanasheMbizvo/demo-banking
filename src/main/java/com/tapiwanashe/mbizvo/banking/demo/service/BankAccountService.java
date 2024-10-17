package com.tapiwanashe.mbizvo.banking.demo.service;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts(){

        return  bankAccountRepository.findAll();
    }

    public  BankAccount createBankAccount(BankAccount account){


        return  bankAccountRepository.save(account);
    }

    public  BankAccount getOneAccount(Long id){
        var repositoryById = bankAccountRepository.findById(id);
        if(repositoryById.isPresent()){

          return  repositoryById.get();
        }throw  new RuntimeException("Not Found");

    }

    public  void  delete(Long id){
        var repositoryById = bankAccountRepository.findById(id);
        if(repositoryById.isPresent()){
            bankAccountRepository.deleteById(id);
        }
    }
}
