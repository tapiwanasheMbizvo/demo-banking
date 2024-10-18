package com.tapiwanashe.mbizvo.banking.demo.controllers;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(){
        return  ResponseEntity.ok(bankAccountService.getAllBankAccounts());
    }

    @PostMapping
    public  ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount account){

        return  ResponseEntity.ok(bankAccountService.createBankAccount(account));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BankAccount> getOneBankAccount(@PathVariable Long id ){
        return  ResponseEntity.ok(bankAccountService.getOneAccount(id));
    }

    @DeleteMapping("/{id}")
    public  void deleteBankAccount(@PathVariable Long id ){
          bankAccountService.delete(id);
    }


}
