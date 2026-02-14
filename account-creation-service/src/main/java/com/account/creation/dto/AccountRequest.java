package com.account.creation.dto;

import com.account.creation.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotBlank(message = "Customer Name is required.")
    private String customerName;

    @NotBlank(message = "Customer Mobile is required.")
    private String customerMobile;

    @NotBlank(message = "Email is required field.")
    @Email(message = "Invalid email format.")
    private String customerEmail;

    @NotBlank(message = "Address 1 is required.")
    private String address1;

    private String address2;

    @NotNull(message = "Account Type is required.")
    private String accountType;
}
