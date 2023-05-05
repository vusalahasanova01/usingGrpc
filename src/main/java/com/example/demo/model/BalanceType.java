package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BalanceType {
    NEXT_PERMANENT_BALANCE("nextPermanentBalance"),
    CURRENT_BALANCE("currentBalance"),
    EXTRA_DEBT_CREDIT("extraDebtCredit"),
    DEBT_CREDIT("debtCredit");

    @Getter
    private final String name;

}
