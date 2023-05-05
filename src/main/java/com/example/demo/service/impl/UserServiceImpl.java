package com.example.demo.service.impl;

import com.example.demo._2a.tables.records.UserMenuJoinRecord;
import com.example.demo._2a.tables.records.UsersRecord;
import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.mapper.MenuRoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserMenuRoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MenuRoleMapper menuRoleMapper;
    private final UserMenuRoleRepository userMenuRoleRepository;


    @Override
    public List<UserResponse> getAllUsers() {
        List<UsersRecord> users = userRepository.getAll();
        return users.stream().map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        UsersRecord usersRecord = userMapper.toUpdateUserRecord(updateUserRequest);
        userRepository.updateUser(usersRecord);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        UsersRecord usersRecord = userMapper.toUserRecordAdd(addUserRequest);
        Long id = userRepository.addUser(usersRecord);
        List<UserMenuJoinRecord> userMenuJoinRecords = menuRoleMapper.toMenuRole(addUserRequest.getUserMenuRoles(), id);
        userMenuRoleRepository.saveUserMenuRoles(userMenuJoinRecords);
    }

}
