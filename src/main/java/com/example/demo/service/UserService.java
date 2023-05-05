package com.example.demo.service;


import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    void updateUser(UpdateUserRequest updateUserRequest);

    void deleteUser(Long id);

    void addUser(AddUserRequest addUserRequest);



}
