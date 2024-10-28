package com.tapiwanashe.mbizvo.banking.demo.service;

import com.tapiwanashe.mbizvo.banking.demo.dto.BankAccountDto;
import com.tapiwanashe.mbizvo.banking.demo.mapper.BankAccountMapper;
import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {


    private static final Logger log = LogManager.getLogger(BankAccountService.class);
    private final BankAccountMapper bankAccountMapper;

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountMapper bankAccountMapper, BankAccountRepository bankAccountRepository) {
        this.bankAccountMapper = bankAccountMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> transferBetweenAccounts(String  fromAccountNumber,
                                                        String toAccountNumber,
                                                        BigDecimal amount) {
        if (fromAccountNumber.equalsIgnoreCase(toAccountNumber)){
            throw new IllegalArgumentException("from and to account cannot be the same ");
        }
        List<BankAccount> bankAccountDtoList = new ArrayList<>();
        var fromRepoByID = bankAccountRepository.findByAccountNumber(fromAccountNumber);
        var toFromRepoByID = bankAccountRepository.findByAccountNumber(toAccountNumber);
        if (fromRepoByID.isPresent() && toFromRepoByID.isPresent()) {
            BankAccount fromBankAccount = fromRepoByID.get();
            BankAccount toBankAccount = toFromRepoByID.get();
            if (fromBankAccount.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance in the from account.");
            }
            fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(amount));
            toBankAccount.setBalance(toBankAccount.getBalance().add(amount));

            bankAccountDtoList.add(fromBankAccount);
            bankAccountDtoList.add(toBankAccount);
            return bankAccountDtoList;
        }throw  new IllegalArgumentException("Verify that both accounts are correct ");

    }

    public  List<BankAccountDto> transferFunds(String fromAccount , String toAccount , BigDecimal amount){
        if (fromAccount.equalsIgnoreCase(toAccount)){
            throw new IllegalArgumentException("from and to account cannot be the same ");
        }

        var optionalFromBankAccount = bankAccountRepository.findByAccountNumber(fromAccount);
        var optionalToBankAccount = bankAccountRepository.findByAccountNumber(toAccount);

        if (optionalToBankAccount.isEmpty() || optionalFromBankAccount.isEmpty()){
        throw  new IllegalArgumentException("Verify that both accounts are correct ");
        }

        List<BankAccountDto> bankAccountDtoList = new ArrayList<>();
        BankAccount fromBankAccount = optionalFromBankAccount.get();
        BankAccount toBankAccount = optionalToBankAccount.get();

        if (fromBankAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the from account.");
        }

        fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(amount));
        toBankAccount.setBalance(toBankAccount.getBalance().add(amount));

        return  bankAccountDtoList;

    }

    public  boolean validateRequest(){

        return  false;
    }



    public List<BankAccountDto> getAllBankAccounts() {

        return bankAccountRepository.findAll().stream()
                .map(bankAccountMapper::toDto).collect(Collectors.toSet()).stream().toList();
    }

    public BankAccountDto createBankAccount(BankAccountDto dto) {

        return bankAccountMapper.toDto(bankAccountRepository.save(bankAccountMapper.toEntity(dto)));
    }

    public BankAccountDto getOneAccount(Long id) {
        var repositoryById = bankAccountRepository.findById(id);
        if (repositoryById.isPresent()) {

            return bankAccountMapper.toDto(repositoryById.get());
        }
        throw new RuntimeException("Not Found");

    }

    public BankAccountDto updateAccount(BankAccountDto dto) {
        var repositoryById = bankAccountRepository.findById(dto.getId());
        if (repositoryById.isPresent()) {
            return bankAccountMapper.toDto(bankAccountRepository.save(bankAccountMapper.toEntity(dto)));
        }
        throw new RuntimeException("Account Not found ");

    }

    public void delete(Long id) {
        var repositoryById = bankAccountRepository.findById(id);
        if (repositoryById.isPresent()) {
            bankAccountRepository.deleteById(id);
        }
    }
}
