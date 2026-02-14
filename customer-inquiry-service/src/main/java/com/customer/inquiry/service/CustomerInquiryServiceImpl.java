package com.customer.inquiry.service;

import com.customer.inquiry.dto.AccountDTO;
import com.customer.inquiry.dto.CustomerInquiryResponse;
import com.customer.inquiry.entity.Customer;
import com.customer.inquiry.repository.CustomerInquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerInquiryServiceImpl implements CustomerInquiryService {

    private final CustomerInquiryRepository customerInquiryRepository;

    @Override
    public CustomerInquiryResponse getCustomerDetails(Long customerNumber) {
        return customerInquiryRepository.findById(customerNumber)
                .map(this::mapToResponse)
                .orElseGet(() -> {
                    CustomerInquiryResponse error = new CustomerInquiryResponse();
                    error.setTransactionStatusCode(401);
                    error.setTransactionStatusDescription("Customer not found");
                    return error;
                });
    }

    private CustomerInquiryResponse mapToResponse(Customer customer) {
        CustomerInquiryResponse res = new CustomerInquiryResponse();
        res.setCustomerNumber(customer.getCustomerNumber());
        res.setCustomerName(customer.getCustomerName());
        res.setCustomerMobile(customer.getCustomerMobile());
        res.setCustomerEmail(customer.getCustomerEmail());
        res.setAddress1(customer.getAddress1());
        res.setAddress2(customer.getAddress2());

        List<AccountDTO> accountDTOs = customer.getAccounts().stream()
                .map(acc -> new AccountDTO(
                        acc.getAccountNumber(),
                        acc.getAccountType().getDescription(),
                        acc.getAvailableBalance()
                ))
                .collect(Collectors.toList());

        res.setAccounts(accountDTOs);
        res.setTransactionStatusCode(302);
        res.setTransactionStatusDescription("Customer Account found");
        return res;
    }
}
