package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.UserDTO;

public interface userService {
    public void insertUser(UserDTO userDTO);
    public void deleteUser(int id);
    public UserDTO getUserById(int id);
    public  UserDTO updateUser(int id,UserDTO userDTO);
}
