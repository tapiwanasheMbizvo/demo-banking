package com.tapiwanashe.mbizvo.banking.demo.controllers;

import com.tapiwanashe.mbizvo.banking.demo.dto.BankAccountDto;
import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.service.BankAccountPagingService;
import com.tapiwanashe.mbizvo.banking.demo.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    private  final BankAccountPagingService bankAccountPagingService;


    @GetMapping("/paged")
    public ResponseEntity<HttpResponse> getAllPagedBankAccounts(@RequestParam Optional<Integer> page,
                                                                        @RequestParam Optional<Integer> size) {


        var bankAccounts = bankAccountPagingService.getBankAccounts(page.orElse(0), size.orElse(5));

        return  ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("Bank Accounts")
                        .status(HttpStatus.OK)
                        .data(Map.of("bank_accounts", bankAccounts))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllBankAccounts(){
        return  ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("bank_accounts", bankAccountService.getAllBankAccounts()))
                        .timeStamp(LocalDateTime.now().toString()).build()
        );
    }

    @PostMapping
    public  ResponseEntity<BankAccountDto> createBankAccount(@RequestBody BankAccountDto account){

        return  ResponseEntity.ok(bankAccountService.createBankAccount(account));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BankAccountDto> getOneBankAccount(@PathVariable Long id ){
        return  ResponseEntity.ok(bankAccountService.getOneAccount(id));
    }

    @DeleteMapping("/{id}")
    public  void deleteBankAccount(@PathVariable Long id ){
          bankAccountService.delete(id);
    }

    @GetMapping("/transfer")
    public ResponseEntity<List<BankAccount>> transferBetweenAccounts(@RequestParam("toAccount") String toAccount,
                                                                     @RequestParam("fromAccount") String fromAccount,
                                                                     @RequestParam("amount") BigDecimal amount
                                                                        ){

        return  ResponseEntity.ok(bankAccountService.transferBetweenAccounts(fromAccount, toAccount, amount));
    }


}
