package com.account.creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Integer transactionStatusCode;

    private String transactionStatusDescription;
}
