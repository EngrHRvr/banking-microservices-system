package com.customer.inquiry.service;

import com.customer.inquiry.dto.CustomerInquiryResponse;

public interface CustomerInquiryService {
    CustomerInquiryResponse getCustomerDetails(Long customerNumber);
}