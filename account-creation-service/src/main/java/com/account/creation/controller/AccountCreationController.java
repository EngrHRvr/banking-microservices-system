package com.account.creation.controller;

import com.account.creation.dto.AccountRequest;
import com.account.creation.dto.AccountResponse;
import com.account.creation.service.AccountCreationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountCreationController {

    private final AccountCreationService creationService;

    public AccountCreationController(AccountCreationService creationService) {
        this.creationService = creationService;
    }

    @PostMapping("/v1/account")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = creationService.createCustomerAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
