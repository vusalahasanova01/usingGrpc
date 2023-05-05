package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCashierResponse {
    private int id;
    private String cashierCode;
    private String name;
    private String type;
    private BigDecimal permanentBalance;
    private BigDecimal currentBalance;
    private BigDecimal debtCredit;
    private BigDecimal extraDebtCredit;
    private BigDecimal remainingDebtCredit;
    private String city;
    private String address;
    private String mobile;
    private Integer status;
    private String fullName; // int convert full name in this line
}
