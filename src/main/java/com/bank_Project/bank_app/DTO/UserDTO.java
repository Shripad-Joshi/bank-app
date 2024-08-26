package com.bank_Project.bank_app.DTO;

import com.bank_Project.bank_app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String name;
    private LocalDate dob;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public UserDTO(){
        createdAt=LocalDateTime.now();
        updateAt=LocalDateTime.now();
    }

    public static User prepareUserEntity(UserDTO userDTO){
        User user=new User();
        user.setUserId(userDTO.userId);
        user.setUsername(userDTO.username);
        user.setName(userDTO.name);
        user.setDob(LocalDate.parse(userDTO.getDob().toString()));
        user.setPassword(userDTO.password);
        user.setEmail(userDTO.email);
        user.setPhoneNumber(userDTO.phoneNumber);
        user.setCreatedAt(userDTO.createdAt);
        user.setUpdateAt(userDTO.updateAt);
        return user;
    }
}
