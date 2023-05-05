package com.example.demo.dto.response;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class AgentResponse {
    private Long id;
    private Long agentNo;
    private String voen;
    private String fullName;
    private String type;
    private int cashiersQty;
    private String city;
    private String address;
    private Integer status;

    private List<GetCashierResponse> cashierResponseList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(Long agentNo) {
        this.agentNo = agentNo;
    }

    public String getVoen() {
        return voen;
    }

    public void setVoen(String voen) {
        this.voen = voen;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCashiersQty() {
        return cashiersQty;
    }

    public void setCashiersQty(int cashiersQty) {
        this.cashiersQty = cashiersQty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GetCashierResponse> getCashierResponseList() {
        return cashierResponseList;
    }

    public void setCashierResponseList(List<GetCashierResponse> cashierResponseList) {
        this.cashierResponseList = cashierResponseList;
    }
}


