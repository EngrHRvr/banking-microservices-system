package com.account.creation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;
    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;
    @Column(name = "customer_mobile", nullable = false, length = 20)
    private String customerMobile;
    @Column(name = "customer_email", nullable = false, length = 50, unique = true)
    private String customerEmail;
    @Column(name = "address1", nullable = false, length = 100)
    private String address1;
    @Column(name = "address2", nullable = false, length = 100)
    private String address2;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

}
