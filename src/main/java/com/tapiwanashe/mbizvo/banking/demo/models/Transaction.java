package com.tapiwanashe.mbizvo.banking.demo.models;


import com.tapiwanashe.mbizvo.banking.demo.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

}
