package com.example.demo.controller;

import com.example.demo.dto.request.user.AddUserRequest;
import com.example.demo.dto.request.user.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @PutMapping()
    public void updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
    }
}
