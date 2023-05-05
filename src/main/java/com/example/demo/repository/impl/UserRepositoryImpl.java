package com.example.demo.repository.impl;

import com.example.demo._2a.tables.records.UsersRecord;
import com.example.demo.model.Status;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo._2a.tables.Users.USERS;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dslContext;

    @Override
    public List<UsersRecord> getAll() {
        return dslContext.selectFrom(USERS)
                .where(USERS.STATUS.ne(3))
                .limit(30)
                .offset((10 - 1) * 30)
                .fetch();
    }

    @Override
    public void updateUser(UsersRecord usersRecord) {
        dslContext.update(USERS)
                .set(USERS.FULL_NAME, usersRecord.getFullName())
                .set(USERS.EMAIL, usersRecord.getEmail())
                .set(USERS.STATUS, usersRecord.getStatus())
                .where(USERS.ID.eq(usersRecord.getId()))
                .execute();
    }

    @Override
    public Long addUser(UsersRecord usersRecord) {
        InsertResultStep<UsersRecord> returning = dslContext.insertInto(USERS)
                .set(USERS.FULL_NAME, usersRecord.getFullName())
                .set(USERS.USER_NAME, usersRecord.getUserName())
                .set(USERS.EMAIL, usersRecord.getEmail())
                .set(USERS.DATE_CREATED, usersRecord.getDateCreated())
                .set(USERS.STATUS, usersRecord.getStatus())
                .returning();
        return  (Long) returning.stream().map(x -> x.get("id")).findFirst().orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        dslContext.update(USERS)
                .set(USERS.STATUS, Status.DELETED.getId())
                .where(USERS.ID.eq(id))
                .execute();
    }

}
