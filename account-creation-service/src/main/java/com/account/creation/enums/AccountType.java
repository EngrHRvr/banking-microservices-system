package com.account.creation.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    S("Savings"),
    C("Checking");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }
}
