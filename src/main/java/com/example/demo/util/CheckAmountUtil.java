package com.example.demo.util;

import com.example.demo.model.ChangeBalance;

import java.math.BigDecimal;

import static com.example.demo.model.ChangeBalance.DECREASE;
import static com.example.demo.model.ChangeBalance.INCREASE;

public class CheckAmountUtil {

    public static BigDecimal checkAmount(ChangeBalance changeBalance, BigDecimal amount) {
        BigDecimal changeAmount = BigDecimal.ZERO;
        if (changeBalance.equals(DECREASE)) {
            changeAmount = amount.negate();
        } else if (changeBalance.equals(INCREASE)) {
            changeAmount = amount;
        } else {
            throw new IllegalArgumentException(String.format("Balance change type is incorrect: %s", changeBalance));
        }
        return changeAmount;
    }

}
