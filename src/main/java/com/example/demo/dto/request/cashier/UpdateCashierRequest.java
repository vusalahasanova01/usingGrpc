package com.example.demo.dto.request.cashier;

import com.example.demo.model.BalanceType;
import com.example.demo.model.ChangeBalance;
import com.example.demo.util.CheckAmountUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCashierRequest {
    private Long id;
    private ChangeBalance changeBalance;
    private BalanceType balanceType;
    private BigDecimal amount;

    public BigDecimal amountByType() {
        return CheckAmountUtil.checkAmount(changeBalance, amount);
    }
}
