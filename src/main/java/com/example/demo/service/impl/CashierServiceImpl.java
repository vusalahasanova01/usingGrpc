package com.example.demo.service.impl;

import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.dto.request.cashier.AddCashierRequest;
import com.example.demo.dto.request.cashier.UpdateAllFieldsCashierRequest;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.dto.response.GetCashierResponse;
import com.example.demo.mapper.CashierMapper;
import com.example.demo.model.BalanceType;
import com.example.demo.repository.CashierRepository;
import com.example.demo.service.CashierService;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;
    private final CashierMapper cashierMapper;

    @Override
    public List<GetCashierResponse> getCashiersByAgent(int agentNo) {
        Result<Record> cashiersByAgent = cashierRepository.getCashiersByAgent(agentNo);
        return cashierMapper.cashierMapper(cashiersByAgent);
    }

    @Override
    public void addCashier(AddCashierRequest addCashierRequest) {
        CashierRecord cashierRecord = cashierMapper.toAddCashierRequest(addCashierRequest);
        cashierRepository.addCashier(cashierRecord);
    }

    @Override
    public void changeCashierStatus(Long cashierId, int statusId) {
        cashierRepository.changeCashierStatus(cashierId, statusId);
    }

    @Override
    public void updateCashier(UpdateAllFieldsCashierRequest cashierRequest) {
        CashierRecord cashierRecord = cashierMapper.toUpdateCashierRequest(cashierRequest);
        cashierRepository.updateCashier(cashierRecord);
    }

    @Override
    public void updateCashierBalance(UpdateCashierRequest updateCashierRequest, BalanceType balanceType) {
        cashierRepository.updateBalance(updateCashierRequest);
    }

//    @Override
//    public GetCashierResponse getCashierById(long cashierId) {
//        Result<Record> cashierById = cashierRepository.getCashierById(cashierId);
//        return cashierMapper.toGetCashierResponse(cashierById);
//
//    }

}
