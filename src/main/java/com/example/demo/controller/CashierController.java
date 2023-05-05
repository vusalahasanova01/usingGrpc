package com.example.demo.controller;


import com.example.demo.dto.request.cashier.AddCashierRequest;
import com.example.demo.dto.request.cashier.UpdateAllFieldsCashierRequest;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.dto.response.GetCashierResponse;
import com.example.demo.model.BalanceType;
import com.example.demo.service.CashierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")
@RequiredArgsConstructor
public class CashierController {

    private final CashierService cashierService;

    @GetMapping("/get-cashiers-by-agent/agentNo/{agentNo}")
    public List<GetCashierResponse> getCashiersByAgent(@PathVariable int agentNo) {
        return cashierService.getCashiersByAgent(agentNo);
    }

    @PostMapping("/add-cashier")
    public void addAgent(@RequestBody AddCashierRequest addCashierRequest) {
        cashierService.addCashier(addCashierRequest);
    }

    @PutMapping("/change-status/cashierId/{cashierId}/statusId/{statusId}")
    public void changeCashierStatus(@PathVariable Long cashierId, @PathVariable int statusId) {
        cashierService.changeCashierStatus(cashierId, statusId);
    }

    @PutMapping("/update-cashier")
    public void updateCashier(@RequestBody UpdateAllFieldsCashierRequest cashierRequest) {
        cashierService.updateCashier(cashierRequest);
    }

    @PutMapping("update-cashier-balance")
    public void updateCashierBalance(@RequestBody UpdateCashierRequest updateCashierRequest, @RequestBody BalanceType balanceType) {
        cashierService.updateCashierBalance(updateCashierRequest, balanceType);
    }
}





