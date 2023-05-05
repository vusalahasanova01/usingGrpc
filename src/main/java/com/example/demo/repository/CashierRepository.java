package com.example.demo.repository;



import com.example.demo._2a.tables.pojos.Cashier;
import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.model.BalanceType;
import com.example.demo.model.ChangeBalance;
import org.jooq.Record;
import org.jooq.Result;

import java.math.BigDecimal;


public interface CashierRepository {

    Result<Record> getCashiersByAgent(int agentNo);

//    Result<Record> getCashierById(long cashierId);

    void addCashier(CashierRecord cashierRecord);

    void changeCashierStatus(Long cashierId, int statusId);

    void updateCashier(CashierRecord cashierRecord);

    void updateBalance(UpdateCashierRequest updateCashierRequest);


}
