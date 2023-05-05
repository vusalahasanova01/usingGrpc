package com.example.demo.repository.impl;


import com.example.demo._2a.tables.records.UserMenuJoinRecord;
import com.example.demo.repository.UserMenuRoleRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserMenuRoleImpl implements UserMenuRoleRepository {
    private final DSLContext dslContext;
    @Override
    public void saveUserMenuRoles(List<UserMenuJoinRecord> userMenuJoinRecords) {

        UserMenuJoinRecord[] records = userMenuJoinRecords.toArray(new UserMenuJoinRecord[0]);
        dslContext.batchInsert(records)
                .execute();
//        for (UserMenuJoinRecord record : userMenuJoinRecords) {
//            dslContext.insertInto(USER_MENU_JOIN)
//                    .set(USER_MENU_JOIN.MENU_ID, record.getMenuId())
//                    .set(USER_MENU_JOIN.ROLE_ID, record.getRoleId())
//                    .set(USER_MENU_JOIN.USER_ID, record.getUserId())
//                    .execute();
//        }
    }
}
