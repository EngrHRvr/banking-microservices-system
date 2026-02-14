package com.account.creation.service;

import com.account.creation.dto.AccountRequest;
import com.account.creation.dto.AccountResponse;
import com.account.creation.entity.Account;
import com.account.creation.entity.Customer;
import com.account.creation.enums.AccountType;
import com.account.creation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountCreationServiceImpl implements AccountCreationService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public AccountResponse createCustomerAccount(AccountRequest request) {

        try {
            AccountType.valueOf(request.getAccountType().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return new AccountResponse(
                    null,
                    400,
                    "Invalid Account Type. Please use 'S' for Savings or 'C' for Checking."
            );
        }
        Customer customer = customerRepository.findByCustomerEmail(request.getCustomerEmail())
                .orElseGet(() -> createNewCustomer(request));

        Account account = new Account();
        account.setAccountType(AccountType.valueOf(request.getAccountType()));
        account.setAvailableBalance(0.0);
        account.setCustomer(customer);

        customer.getAccounts().add(account);

        Customer savedCustomer = customerRepository.save(customer);

        return new AccountResponse(
                savedCustomer.getCustomerNumber(),
                201,
                "Account created successfully"
        );
    }

    private Customer createNewCustomer(AccountRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(request.getCustomerName());
        newCustomer.setCustomerMobile(request.getCustomerMobile());
        newCustomer.setCustomerEmail(request.getCustomerEmail());
        newCustomer.setAddress1(request.getAddress1());
        newCustomer.setAddress2(request.getAddress2());
        return newCustomer;
    }
}