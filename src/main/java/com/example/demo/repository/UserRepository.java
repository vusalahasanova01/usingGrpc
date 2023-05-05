package com.example.demo.repository;

import com.example.demo._2a.tables.records.UsersRecord;

import java.util.List;

public interface UserRepository {

    List<UsersRecord> getAll();

    void updateUser(UsersRecord usersRecord);

    Long addUser(UsersRecord usersRecord);

    void deleteUser(Long id);



}
