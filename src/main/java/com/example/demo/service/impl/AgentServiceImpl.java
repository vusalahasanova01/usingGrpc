package com.example.demo.service.impl;


import com.example.demo._2a.tables.pojos.Agents;
import com.example.demo._2a.tables.records.AgentsRecord;
import com.example.demo._2a.tables.records.CashierRecord;
import com.example.demo.dto.request.agent.AddAgentRequest;
import com.example.demo.dto.request.agent.UpdateAgentRequest;
import com.example.demo.dto.response.AgentResponse;
import com.example.demo.dto.response.GetCashierResponse;
import com.example.demo.mapper.AgentMapper;
import com.example.demo.mapper.CashierMapper;
import com.example.demo.repository.AgentRepository;
import com.example.demo.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final CashierMapper cashierMapper;
    private final AgentMapper agentMapper;

    @Override
    public List<AgentResponse> getAllAgents(int limit, int offset) {
        List<AgentsRecord> agents = agentRepository.getAllAgents(limit,offset);
        return agents.stream()
                .map(agentMapper::toAgent)
                .collect(Collectors.toList());
    }

    @Override
    public void addAgent(AddAgentRequest addAgentRequest) {
        AgentsRecord agentsRecord = agentMapper.toAddAgentRequest(addAgentRequest);
        agentRepository.addAgent(agentsRecord);
    }

    @Override
    public void deleteAgent(Long id) {
        agentRepository.deleteAgent(id);
    }


    @Override
    public void updateAgent(UpdateAgentRequest agentRequest) {
        AgentsRecord agentsRecord = agentMapper.toAgentRequest(agentRequest);
        agentRepository.updateAgent(agentsRecord);
    }

    @Override
    public void changeStatusAgent(Long agentId, int statusId) {
        agentRepository.changeStatusAgent(agentId, statusId);
    }

    @Override
    public List<AgentResponse> getAllAgentsWithCashiersV2() {
        return agentRepository.getAllAgentsWithCashiersV2()
                .stream().map(entry -> {
                    AgentResponse agentResponse = entry.getKey().into(AgentResponse.class);
                    List<CashierRecord> cashiers = entry.getValue();
                    List<GetCashierResponse> cashierResponses = cashierMapper.cashierMapper2(cashiers);
                    agentResponse.setCashierResponseList(cashierResponses);
                    return agentResponse;
                }).collect(Collectors.toList());
    }

}
