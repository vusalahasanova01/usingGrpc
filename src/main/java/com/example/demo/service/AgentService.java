package com.example.demo.service;

import com.example.demo.dto.request.agent.AddAgentRequest;
import com.example.demo.dto.request.agent.UpdateAgentRequest;
import com.example.demo.dto.response.AgentResponse;

import java.util.List;

public interface AgentService {

    List<AgentResponse> getAllAgents(int limit, int offset);

    void addAgent(AddAgentRequest addAgentRequest);

    void deleteAgent(Long id);

    void updateAgent(UpdateAgentRequest agentRequest);

    void changeStatusAgent(Long agentId, int statusId);


    List<AgentResponse> getAllAgentsWithCashiersV2();

}
