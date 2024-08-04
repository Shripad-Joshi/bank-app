package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.entity.User;
import com.bank_Project.bank_app.repository.userRepository;
import com.bank_Project.bank_app.service.userService;
import org.springframework.beans.factory.annotation.Autowired;

public class userServiceImpl implements userService {

    @Autowired
    userRepository userRepository;

    @Override
    public void insertUser(UserDTO userDTO) {
        User user=UserDTO.prepareUserEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user=userRepository.getById(id);
        return User.prepareUserDTO(user);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user=userRepository.getById(id);
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDob(userDTO.getDob());
        return User.prepareUserDTO(user);
    }
}
