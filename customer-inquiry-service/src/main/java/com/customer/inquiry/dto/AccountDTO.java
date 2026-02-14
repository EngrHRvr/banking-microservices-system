package com.customer.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private Double availableBalance;
}
