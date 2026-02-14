package com.account.creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long customerNumber;

    private Integer transactionStatusCode;

    private String transactionStatusDescription;
}