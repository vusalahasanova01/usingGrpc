package com.example.demo.repository.impl;

import com.example.demo._2a.Tables;
import com.example.demo._2a.tables.pojos.Cashier;
import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.dto.request.cashier.UpdateCashierRequest;
import com.example.demo.model.Status;
import com.example.demo.repository.CashierRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

import static com.example.demo._2a.Tables.AGENTS;
import static com.example.demo._2a.tables.Cashier.CASHIER;


@Repository
@RequiredArgsConstructor
public class CashierRepositoryImpl implements CashierRepository {

    private final DSLContext dslContext;

    @Override
    public Result<Record> getCashiersByAgent(int agentNo) {
        return dslContext.select()
                .from(CASHIER)
                .join(AGENTS).on(CASHIER.AGENT_ID.eq(AGENTS.AGENT_NO))
                .where(CASHIER.AGENT_ID.eq((long) agentNo))
                .fetch();
    }

//    @Override
//    public Result<Record> getCashierById(long cashierId) {
//         dslContext.select()
//                .from(CASHIER)
//                .where(CASHIER.STATUS.ne(Status.DELETED.getId()).and(CASHIER.ID.eq(cashierId))).stream().findFirst()
//                .fetch();
//    }

    @Override
    public void addCashier(CashierRecord cashierRecord) {
        dslContext.insertInto(CASHIER)
                .set(CASHIER.AGENT_ID, cashierRecord.getAgentId())
                .set(CASHIER.ID, cashierRecord.getId())
                .set(CASHIER.CASHIER_CODE, cashierRecord.getCashierCode())
                .set(CASHIER.NAME, cashierRecord.getName())
                .set(CASHIER.TYPE, cashierRecord.getType())
                .set(CASHIER.NEXT_PERMANENT_BALANCE, cashierRecord.getNextPermanentBalance())
                .set(CASHIER.CURRENT_BALANCE, cashierRecord.getCurrentBalance())
                .set(CASHIER.DEBT_CREDIT, cashierRecord.getDebtCredit())
                .set(CASHIER.EXTRA_DEBT_CREDIT, cashierRecord.getExtraDebtCredit())
                .set(CASHIER.CITY, cashierRecord.getCity())
                .set(CASHIER.ADDRESS, cashierRecord.getAddress())
                .set(CASHIER.MOBILE, cashierRecord.getMobile())
                .set(CASHIER.STATUS, cashierRecord.getStatus())
                .execute();
    }

    @Override
    public void changeCashierStatus(Long cashierId, int statusId) {
        Long agentId = dslContext.update(Tables.CASHIER)
                .set(Tables.CASHIER.STATUS, statusId)
                .where(Tables.CASHIER.ID.eq(cashierId))
                .returning(CASHIER.AGENT_ID).fetchOneInto(Long.class);

        boolean result = dslContext.fetchExists(
                CASHIER, CASHIER.AGENT_ID.eq(agentId).and(CASHIER.STATUS.eq(1)));

        if (!result) {
            dslContext.update(AGENTS)
                    .set(AGENTS.STATUS, 0)
                    .where(AGENTS.ID.eq(CASHIER.AGENT_ID))
                    .execute();
        }
    }

    @Override
    public void updateCashier(CashierRecord cashierRecord) {
        dslContext.update(Tables.CASHIER)
                .set(CASHIER.CASHIER_CODE, cashierRecord.getCashierCode())
                .set(CASHIER.NAME, cashierRecord.getName())
                .set(CASHIER.TYPE, cashierRecord.getType())
                .set(CASHIER.NEXT_PERMANENT_BALANCE, cashierRecord.getNextPermanentBalance())
                .set(CASHIER.CURRENT_BALANCE, cashierRecord.getCurrentBalance())
                .set(CASHIER.DEBT_CREDIT, cashierRecord.getDebtCredit())
                .set(CASHIER.EXTRA_DEBT_CREDIT, cashierRecord.getExtraDebtCredit())
                .set(CASHIER.CITY, cashierRecord.getCity())
                .set(CASHIER.ADDRESS, cashierRecord.getAddress())
                .set(CASHIER.MOBILE, cashierRecord.getMobile())
                .set(CASHIER.STATUS, cashierRecord.getStatus())
                .where(Tables.CASHIER.ID.eq(cashierRecord.getId()))
                .execute();
    }

    @Override
    public void updateBalance(UpdateCashierRequest updateCashierRequest) {
        BigDecimal amount = updateCashierRequest.amountByType();
        UpdateQuery<CashierRecord> query = dslContext.updateQuery(CASHIER);
        switch (updateCashierRequest.getBalanceType()) {
            case DEBT_CREDIT -> query.addValue(CASHIER.DEBT_CREDIT, CASHIER.DEBT_CREDIT.add(updateCashierRequest.getAmount()));
            case NEXT_PERMANENT_BALANCE ->
                    query.addValue(CASHIER.NEXT_PERMANENT_BALANCE, CASHIER.NEXT_PERMANENT_BALANCE.add(updateCashierRequest.getAmount()));
            case CURRENT_BALANCE -> query.addValue(CASHIER.CURRENT_BALANCE, CASHIER.CURRENT_BALANCE.add(updateCashierRequest.getAmount()));
            case EXTRA_DEBT_CREDIT -> query.addValue(CASHIER.EXTRA_DEBT_CREDIT, CASHIER.EXTRA_DEBT_CREDIT.add(updateCashierRequest.getAmount()));
        }
        query.addConditions(CASHIER.ID.eq(updateCashierRequest.getId()));
        query.execute();
    }
}
