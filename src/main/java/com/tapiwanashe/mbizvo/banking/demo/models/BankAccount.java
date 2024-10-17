package com.tapiwanashe.mbizvo.banking.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {
    @Id
    private Long id;
    private  String accountNumber;
    private BigDecimal balance;
}
