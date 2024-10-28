package com.tapiwanashe.mbizvo.banking.demo.repositories;

import com.tapiwanashe.mbizvo.banking.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountNumber(String accountNumber);
}
