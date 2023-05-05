package com.example.demo.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private LocalDate dateCreated;
    private int status;
}
