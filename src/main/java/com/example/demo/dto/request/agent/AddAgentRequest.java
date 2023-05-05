package com.example.demo.dto.request.agent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAgentRequest {
    private Long agentNo;
    private String voen;
    private String fullName;
    private String type;
    private int cashiersQty;
    private String city;
    private String address;
    private Integer status;
}
