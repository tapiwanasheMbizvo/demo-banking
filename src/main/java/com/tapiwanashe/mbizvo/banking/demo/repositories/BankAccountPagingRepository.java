package com.tapiwanashe.mbizvo.banking.demo.repositories;

import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BankAccountPagingRepository  extends PagingAndSortingRepository<BankAccount, Long> {


}
