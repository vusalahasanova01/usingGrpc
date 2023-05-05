package com.example.demo.mapper;

import com.example.demo._2a.tables.records.UsersRecord;
import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.util.TimeProvider;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    private TimeProvider timeProvider;

    public UserResponse toUserResponse(UsersRecord usersRecord){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(usersRecord.getId());
        userResponse.setUserName(usersRecord.getUserName());
        userResponse.setEmail(usersRecord.getEmail());
        userResponse.setFullName(usersRecord.getFullName());
        userResponse.setStatus(usersRecord.getStatus());
        //userResponse.setDateCreated();
        return userResponse;
    }

    public UsersRecord toUpdateUserRecord(UpdateUserRequest updateUserRequest) {
        UsersRecord usersRecord = new UsersRecord();
        usersRecord.setUserName(updateUserRequest.getUserName());
        usersRecord.setEmail(updateUserRequest.getEmail());
        usersRecord.setFullName(updateUserRequest.getFullName());
        usersRecord.setStatus(updateUserRequest.getStatus());
        usersRecord.setDateCreated(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli());
        return usersRecord;
    }

    public UsersRecord toUserRecordAdd(AddUserRequest addUserRequest){
        UsersRecord usersRecord = new UsersRecord();
        usersRecord.setUserName(addUserRequest.getUserName());
        usersRecord.setEmail(addUserRequest.getEmail());
        usersRecord.setFullName(addUserRequest.getFullName());
        usersRecord.setStatus(0);
        usersRecord.setDateCreated(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli());
        return usersRecord;
    }

    @Named("toCurrentDate")
    public LocalDate toCurrentDate(LocalDate localDate) {
        return timeProvider.getCurrentDate();
    }

    @Autowired
    protected void setTimeProvider(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

}
