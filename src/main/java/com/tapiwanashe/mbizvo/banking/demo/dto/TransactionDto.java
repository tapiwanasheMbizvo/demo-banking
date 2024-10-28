package com.tapiwanashe.mbizvo.banking.demo.dto;

import com.tapiwanashe.mbizvo.banking.demo.models.enums.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransactionDto {
    private BigDecimal amount;
    private String accountNumber;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
}
