package com.tapiwanashe.mbizvo.banking.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String accountNumber;
    private BigDecimal balance;
}
