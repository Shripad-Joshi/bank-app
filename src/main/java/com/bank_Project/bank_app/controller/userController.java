package com.bank_Project.bank_app.controller;

import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.service.Impl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class userController {

    @Autowired
    userServiceImpl userService;

    @PostMapping
    public void addUser(UserDTO userDTO){
        userService.insertUser(userDTO);
    }

    @GetMapping
    public UserDTO getUser(int id){
        return userService.getUserById(id);
    }

    @DeleteMapping
    public void deleteUser(int id){
        userService.deleteUser(id);
    }

    @PostMapping
    public UserDTO updateUser(int id,UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }
}
