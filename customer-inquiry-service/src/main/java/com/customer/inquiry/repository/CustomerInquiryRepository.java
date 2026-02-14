package com.customer.inquiry.repository;

import com.customer.inquiry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInquiryRepository extends JpaRepository<Customer, Long> {
}
