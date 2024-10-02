package com.bank_Project.bank_app.entity;

import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames={"username","email","phoneNumber"})
})
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private LocalDate dob;
    @Column(nullable = false)
    private String username;
    private String password;
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(name="created_At",updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_At")
    private LocalDateTime updateAt;

    public static UserDTO prepareUserDTO(User userEntity){
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setName(userEntity.getName());
        userDTO.setDob(userEntity.getDob());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setUpdateAt(userEntity.getUpdateAt());
        return userDTO;
    }
}
