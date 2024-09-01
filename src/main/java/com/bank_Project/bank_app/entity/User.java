package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private LocalDate dob;
    @Column(nullable = false,unique = true)
    private String username;
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(name="created_At",updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_At")
    private LocalDateTime updateAt;

    public static UserDTO prepareUserDTO(User userEntity){
        UserDTO userDTO=new UserDTO();
        System.out.println(userEntity.getUserId());
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setName(userEntity.getName());
        userDTO.setDob(userEntity.getDob());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setUpdateAt(userEntity.getUpdateAt());
        return userDTO;
    }
}
