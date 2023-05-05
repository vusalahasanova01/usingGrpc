package com.example.demo.config;


import com.example.demo.mapper.AgentMapper;
import com.example.demo.mapper.CashierMapper;
import com.example.demo.mapper.MenuRoleMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public AgentMapper agentMapper() {
        return AgentMapper.INSTANCE;
    }

    @Bean
    public CashierMapper cashierMapper() {
        return CashierMapper.INSTANCE;
    }

    @Bean
    public UserMapper userMapper(){
        return UserMapper.INSTANCE;
    }

    @Bean
    public MenuRoleMapper menuRoleMapper(){
        return MenuRoleMapper.INSTANCE;
    }



}
