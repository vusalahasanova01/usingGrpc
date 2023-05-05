package com.example.demo.controller;

import com.example.demo.dto.request.agent.AddAgentRequest;
import com.example.demo.dto.request.agent.UpdateAgentRequest;
import com.example.demo.dto.response.AgentResponse;
import com.example.demo.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/get-all-agents")
    public List<AgentResponse> getAllTickets(@PathVariable int limit,@PathVariable int offset) {
        return agentService.getAllAgents(limit,offset);
    }

    @PostMapping("/add-agent")
    public void addAgent(@RequestBody AddAgentRequest addAgentRequest) {
        agentService.addAgent(addAgentRequest);
    }

    @DeleteMapping("/delete/id/{id}")
    void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }

    @PutMapping("/change-status/agentId/{agentId}/statusId/{statusId}")
    public void changeStatusAgent(@PathVariable Long agentId, @PathVariable int statusId) {
        agentService.changeStatusAgent(agentId, statusId);
    }

    @GetMapping("get-all-agents-cashiers-v2")
    public List<AgentResponse> getAllAgentsWithCashiersV2() {
        return agentService.getAllAgentsWithCashiersV2();
    }

    @PutMapping("/update-agent")
    public void updateAgent(@RequestBody UpdateAgentRequest agentRequest) {
        agentService.updateAgent(agentRequest);
    }

}