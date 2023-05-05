package com.example.demo.service;

import com.example.demo.dto.request.cashier.AddCashierRequest;
import com.example.demo.dto.request.cashier.UpdateAllFieldsCashierRequest;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.dto.response.GetCashierResponse;
import com.example.demo.model.BalanceType;

import java.util.List;

public interface CashierService {

    List<GetCashierResponse> getCashiersByAgent(int agentNo);

    void addCashier(AddCashierRequest addCashierRequest);

    void changeCashierStatus(Long cashierId, int statusId);

    void updateCashier(UpdateAllFieldsCashierRequest cashierRequest);

    void updateCashierBalance(UpdateCashierRequest updateCashierRequest, BalanceType balanceType);

//    GetCashierResponse getCashierById(long cashierId);


}
