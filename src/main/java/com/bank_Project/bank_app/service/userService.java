package com.bank_Project.bank_app.service;

import com.bank_Project.bank_app.DTO.UserDTO;

public interface userService {
    public UserDTO insertUser(UserDTO userDTO);
    public UserDTO deleteUser(Long id);
    public UserDTO getUserById(Long id);
    public  UserDTO updateUser(Long id,UserDTO userDTO);
    public String checkUser(Long id);
}
