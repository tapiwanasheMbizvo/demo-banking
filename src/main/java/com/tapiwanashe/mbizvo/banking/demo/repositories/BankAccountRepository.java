package com.tapiwanashe.mbizvo.banking.demo.repositories;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository  extends JpaRepository<BankAccount, Long> {

}
