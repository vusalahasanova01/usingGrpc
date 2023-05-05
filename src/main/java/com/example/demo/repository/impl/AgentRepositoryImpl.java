package com.example.demo.repository.impl;

import com.example.demo._2a.tables.pojos.Agents;

import com.example.demo._2a.tables.records.AgentsRecord;
import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.model.Status;
import com.example.demo.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.demo._2a.tables.Agents.AGENTS;
import static com.example.demo._2a.tables.Cashier.CASHIER;


@Repository
@RequiredArgsConstructor
public class AgentRepositoryImpl implements AgentRepository {

    private final DSLContext dslContext;

    @Override
    public List<AgentsRecord> getAllAgents(int limit, int offset) {
        return dslContext.selectFrom(AGENTS)
                .where(AGENTS.STATUS.ne(3))
                //.orderBy(AGENTS.ID.asc())
                .limit(limit)
                .offset(offset)
                .fetchInto(AgentsRecord.class);
//.limit(pageSize)
//    .offset((pageNumber - 1) * pageSize)
    }


    @Override
    public void addAgent(AgentsRecord agentsRecord) {
        dslContext.insertInto(AGENTS)
                .set(AGENTS.VOEN, agentsRecord.getVoen())
                .set(AGENTS.FULL_NAME, agentsRecord.getFullName())
                .set(AGENTS.TYPE, agentsRecord.getType())
                .set(AGENTS.CASHIERS_QTY, agentsRecord.getCashiersQty())
                .set(AGENTS.CITY, agentsRecord.getCity())
                .set(AGENTS.ADDRESS, agentsRecord.getAddress())
                .set(AGENTS.STATUS, agentsRecord.getStatus())
                .execute();
    }

    @Override
    public void deleteAgent(Long id) {
        dslContext.update(AGENTS)
                .set(AGENTS.STATUS, Status.DELETED.getId())
                .where(AGENTS.ID.eq(id))
                .execute();
    }

    @Override
    public void changeStatusAgent(Long agentId, int statusId) {
        Query query = dslContext.update(AGENTS)
                .set(AGENTS.STATUS, statusId)
                .where(AGENTS.ID.eq(agentId));

        Query query2 = dslContext.update(CASHIER)
                .set(CASHIER.STATUS, statusId)
                .where(CASHIER.AGENT_ID.eq(agentId));

        dslContext.batch(query, query2).execute();
    }

    @Override
    public void updateAgent(AgentsRecord agentsRecord) {
        dslContext.update(AGENTS)
                .set(AGENTS.AGENT_NO, agentsRecord.getAgentNo())
                .set(AGENTS.VOEN, agentsRecord.getVoen())
                .set(AGENTS.FULL_NAME, agentsRecord.getFullName())
                .set(AGENTS.TYPE, agentsRecord.getType())
                .set(AGENTS.CASHIERS_QTY, agentsRecord.getCashiersQty())
                .set(AGENTS.CITY, agentsRecord.getCity())
                .set(AGENTS.ADDRESS, agentsRecord.getAddress())
                .set(AGENTS.STATUS, agentsRecord.getStatus())
                .where(AGENTS.ID.eq(agentsRecord.getId()))
                .execute();
    }

    @Override
    public List<Map.Entry<AgentsRecord, List<CashierRecord>>> getAllAgentsWithCashiersV2() {
        return new ArrayList<>(dslContext.select()
                .from(AGENTS)
                .leftJoin(CASHIER).on(AGENTS.ID.eq(CASHIER.AGENT_ID))
                .fetchGroups(AgentsRecord.class, CashierRecord.class)
                .entrySet());
    }
}
