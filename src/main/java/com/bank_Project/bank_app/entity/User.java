package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.DTO.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    private int userId;
    private String name;
    private LocalDateTime dob;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @Column(name="created_At",updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_At")
    private LocalDateTime updateAt;

    public static UserDTO prepareUserDTO(User userEntity){
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(userEntity.userId);
        userDTO.setUsername(userEntity.username);
        userDTO.setName(userEntity.name);
        userDTO.setDob(userEntity.dob);
        userDTO.setPassword(userEntity.password);
        userDTO.setEmail(userEntity.email);
        userDTO.setPhoneNumber(userEntity.phoneNumber);
        userDTO.setCreatedAt(userEntity.createdAt);
        userDTO.setUpdateAt(userEntity.updateAt);
        return userDTO;
    }
}
