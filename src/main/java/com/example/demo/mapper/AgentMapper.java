package com.example.demo.mapper;

import com.example.demo._2a.tables.pojos.Agents;
import com.example.demo._2a.tables.records.AgentsRecord;
import com.example.demo.dto.request.agent.AddAgentRequest;
import com.example.demo.dto.request.agent.UpdateAgentRequest;
import com.example.demo.dto.response.AgentResponse;
import com.example.demo.dto.response.GetCashierResponse;
import org.jooq.Record;
import org.jooq.Result;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

import static com.example.demo._2a.tables.Agents.AGENTS;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgentMapper {

    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

   //AgentResponse toAgent(Agents agents);

    default AgentResponse toAgent(AgentsRecord agentsRecord){
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setId(agentsRecord.getId());
        agentResponse.setAgentNo(agentsRecord.getAgentNo());
        agentResponse.setVoen(agentsRecord.getVoen());
        agentResponse.setFullName(agentsRecord.getFullName());
        agentResponse.setType(agentsRecord.getType());
        agentResponse.setCashiersQty(agentsRecord.getCashiersQty());
        agentResponse.setCity(agentsRecord.getCity());
        agentResponse.setAddress(agentsRecord.getAddress());
        agentResponse.setStatus(agentsRecord.getStatus());
        return agentResponse;
    }
    
    default AgentsRecord toAgentRequest(UpdateAgentRequest updateAgentRequest){
        AgentsRecord agentsRecord = new AgentsRecord();
        agentsRecord.setId(updateAgentRequest.getId());
        agentsRecord.setAgentNo(updateAgentRequest.getAgentNo());
        agentsRecord.setVoen(updateAgentRequest.getVoen());
        agentsRecord.setFullName(updateAgentRequest.getFullName());
        agentsRecord.setType(updateAgentRequest.getType());
        agentsRecord.setCashiersQty(updateAgentRequest.getCashiersQty());
        agentsRecord.setCity(updateAgentRequest.getCity());
        agentsRecord.setAddress(updateAgentRequest.getAddress());
        agentsRecord.setStatus(updateAgentRequest.getStatus());
        return agentsRecord;
    }

    default AgentsRecord toAddAgentRequest(AddAgentRequest addAgentRequest){
        AgentsRecord agentsRecord = new AgentsRecord();
        agentsRecord.setAgentNo(addAgentRequest.getAgentNo());
        agentsRecord.setVoen(addAgentRequest.getVoen());
        agentsRecord.setFullName(addAgentRequest.getFullName());
        agentsRecord.setType(addAgentRequest.getType());
        agentsRecord.setCashiersQty(addAgentRequest.getCashiersQty());
        agentsRecord.setCity(addAgentRequest.getCity());
        agentsRecord.setAddress(addAgentRequest.getAddress());
        agentsRecord.setStatus(0);
        return agentsRecord;
    }

    default AgentResponse toResponse(Result<Record> result) {

        Optional<AgentResponse> response = result.stream().findFirst()
                .map(record -> {
                    AgentResponse agentResponse = new AgentResponse();
                    agentResponse.setId(record.get(AGENTS.ID));
                    agentResponse.setAgentNo(record.get(AGENTS.AGENT_NO));
                    agentResponse.setVoen(record.get(AGENTS.VOEN));
                    agentResponse.setFullName(record.get(AGENTS.FULL_NAME));
                    agentResponse.setType(record.get(AGENTS.TYPE));
                    agentResponse.setCashiersQty(record.get(AGENTS.CASHIERS_QTY));
                    agentResponse.setCity(record.get(AGENTS.CITY));
                    agentResponse.setAddress(record.get(AGENTS.ADDRESS));
                    agentResponse.setStatus(record.get(AGENTS.STATUS));
                    return agentResponse;
                });

       return response.map(agent -> {
            List<GetCashierResponse> cashierResponses = CashierMapper.INSTANCE.cashierMapper(result);
            agent.setCashierResponseList(cashierResponses);
            return agent;
        }).orElse(null);

    }
}
