package com.example.demo.dto.request.cashier;

import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAllFieldsCashierRequest {
    private Long id;
    private String cashierCode;
    private String name;
    private String type;
    private BigDecimal nextpermanentBalance;
    private BigDecimal currentBalance;
    private BigDecimal debtCredit;
    private BigDecimal extraDebtCredit;
    private BigDecimal remainingDebtCredit;
    private String city;
    private String address;
    private String mobile;
    private Status status;
    private Long agentId;
}
