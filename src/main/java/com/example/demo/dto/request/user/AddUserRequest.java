package com.example.demo.dto.request.user;

import com.example.demo.dto.UserMenuRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    private String fullName;
    private String userName;
    private String email;

//    public LocalDate getDateCreated() {
//        return null;
//    }
    private List<UserMenuRole> userMenuRoles;

    //private HashMap<String, String> roles; List yaradib icine obyekt yaziriq
    // List<object> object;
}
