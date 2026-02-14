package com.customer.inquiry.service;

import com.customer.inquiry.dto.CustomerInquiryResponse;
import com.customer.inquiry.entity.Account;
import com.customer.inquiry.entity.Customer;
import com.customer.inquiry.enums.AccountType;
import com.customer.inquiry.repository.CustomerInquiryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInquiryServiceTest {

    @Mock
    private CustomerInquiryRepository customerInquiryRepository;

    @InjectMocks
    private CustomerInquiryServiceImpl customerInquiryService;

    private final String CUSTOMER_NAME = "Test Name";
    private final String CUSTOMER_NOT_FOUND = "Customer not found";
    @Test
    public void testCustomerInquiry_Success() {
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerNumber(1L);
        mockCustomer.setCustomerName(CUSTOMER_NAME);
        mockCustomer.setAccounts(new ArrayList<>());

        when(customerInquiryRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));

        CustomerInquiryResponse response = customerInquiryService.getCustomerDetails(1L);

        assertEquals(CUSTOMER_NAME, response.getCustomerName());
        assertEquals(Integer.valueOf(302), response.getTransactionStatusCode());
    }

    @Test
    public void testInquiry_NotFound() {
        when(customerInquiryRepository.findById(99L)).thenReturn(Optional.empty());

        CustomerInquiryResponse response = customerInquiryService.getCustomerDetails(99L);

        assertEquals(Integer.valueOf(401), response.getTransactionStatusCode());
        assertEquals(CUSTOMER_NOT_FOUND, response.getTransactionStatusDescription());
    }
}
