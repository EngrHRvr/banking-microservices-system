package com.account.creation.service;

import com.account.creation.dto.AccountRequest;
import com.account.creation.dto.AccountResponse;

public interface AccountCreationService {
    AccountResponse createCustomerAccount(AccountRequest request);
}
