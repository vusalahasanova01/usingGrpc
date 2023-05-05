package com.example.demo.repository;



import com.example.demo._2a.tables.records.UserMenuJoinRecord;

import java.util.List;

public interface UserMenuRoleRepository {

    void saveUserMenuRoles(List<UserMenuJoinRecord> userMenuJoinRecords);
}
