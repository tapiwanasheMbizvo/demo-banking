package com.tapiwanashe.mbizvo.banking.demo.service;


import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.repositories.BankAccountPagingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BankAccountPagingService {
    private final BankAccountPagingRepository repository;
    public Page<BankAccount> getBankAccounts(int page , int size){
        return  repository.findAll(PageRequest.of(page, size));
    }

}
