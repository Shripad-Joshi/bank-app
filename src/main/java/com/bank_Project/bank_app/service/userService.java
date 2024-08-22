package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.UserDTO;

public interface userService {
    public UserDTO insertUser(UserDTO userDTO);
    public UserDTO deleteUser(int id);
    public UserDTO getUserById(int id);
    public  UserDTO updateUser(int id,UserDTO userDTO);

    public String checkUser(int id);
}
