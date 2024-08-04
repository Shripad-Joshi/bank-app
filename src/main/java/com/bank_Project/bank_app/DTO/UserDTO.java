package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String username;
    private String name;
    private LocalDateTime dob;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public static User prepareUserEntity(UserDTO userDTO){
        User user=new User();
        user.setUserId(userDTO.userId);
        user.setUsername(userDTO.username);
        user.setName(userDTO.name);
        user.setDob(userDTO.getDob());
        user.setPassword(userDTO.password);
        user.setEmail(userDTO.email);
        user.setPhoneNumber(userDTO.phoneNumber);
        user.setCreatedAt(userDTO.createdAt);
        user.setUpdateAt(userDTO.updateAt);
        return user;
    }
}
