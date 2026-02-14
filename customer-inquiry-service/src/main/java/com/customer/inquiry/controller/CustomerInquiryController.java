package com.customer.inquiry.controller;

import com.customer.inquiry.dto.CustomerInquiryResponse;
import com.customer.inquiry.service.CustomerInquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class CustomerInquiryController {

    private final CustomerInquiryService customerInquiryService;

    public CustomerInquiryController(CustomerInquiryService customerInquiryService) {
        this.customerInquiryService = customerInquiryService;
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerInquiryResponse> getCustomer(@PathVariable Long customerNumber) {
        CustomerInquiryResponse response = customerInquiryService.getCustomerDetails(customerNumber);
        HttpStatus status = response.getTransactionStatusCode() == 302 ?
                HttpStatus.FOUND : HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<>(response, status);
    }
}
