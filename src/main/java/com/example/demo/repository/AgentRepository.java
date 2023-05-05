package com.example.demo.repository;


import com.example.demo._2a.tables.pojos.Agents;
import com.example.demo._2a.tables.records.AgentsRecord;
import com.example.demo._2a.tables.records.CashierRecord;

import java.util.List;
import java.util.Map;

public interface AgentRepository {

    List<AgentsRecord> getAllAgents(int limit, int offset);

    void addAgent(AgentsRecord agentsRecord);

    void deleteAgent(Long id);

    void changeStatusAgent(Long agentId, int statusId);

    void updateAgent(AgentsRecord agentsRecord);

    List<Map.Entry<AgentsRecord, List<CashierRecord>>> getAllAgentsWithCashiersV2();
}
