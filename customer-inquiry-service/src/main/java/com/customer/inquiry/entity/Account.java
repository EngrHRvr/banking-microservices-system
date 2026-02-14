package com.customer.inquiry.entity;

import com.customer.inquiry.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 1)
    private AccountType accountType;

    @Column(name = "available_balance", nullable = false)
    private Double availableBalance = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false)
    private Customer customer;

}

