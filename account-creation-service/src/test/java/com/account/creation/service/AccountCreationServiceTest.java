package com.account.creation.service;

import com.account.creation.dto.AccountRequest;
import com.account.creation.dto.AccountResponse;
import com.account.creation.entity.Customer;
import com.account.creation.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreationServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountCreationServiceImpl accountService;

    private final String CUSTOMER_NAME = "Test Name";
    private final String CUSTOMER_MOBILE_NUMBER = "09123456789";
    private final String CUSTOMER_EMAIL = "test@example.com";
    private final String ADDRESS_1 = "Test Address 1";
    private final String ADDRESS_2 = "Test Address 2";
    private final String ACCOUNT_TYPE = "S";

    @Test
    public void testCreateAccount_Success() {
        AccountRequest request = new AccountRequest();
        request.setCustomerName(CUSTOMER_NAME);
        request.setCustomerMobile(CUSTOMER_MOBILE_NUMBER);
        request.setCustomerEmail(CUSTOMER_EMAIL);
        request.setAddress1(ADDRESS_1);
        request.setAddress2(ADDRESS_2);
        request.setAccountType(ACCOUNT_TYPE);

        when(customerRepository.findByCustomerEmail(anyString())).thenReturn(Optional.empty());

        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerNumber(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        AccountResponse response = accountService.createCustomerAccount(request);

        assertEquals(Integer.valueOf(201), response.getTransactionStatusCode());
        assertEquals(Long.valueOf(1), response.getCustomerNumber());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
