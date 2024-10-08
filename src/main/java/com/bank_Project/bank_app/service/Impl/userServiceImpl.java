package com.bank_Project.bank_app.service.Impl;

import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.advice.ResourceNotFoundException;
import com.bank_Project.bank_app.entity.User;
import com.bank_Project.bank_app.repository.userRepository;
import com.bank_Project.bank_app.service.userService;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    @Autowired
    userRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public userServiceImpl(){
        this.passwordEncoder=null;
    }

    public userServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO insertUser(UserDTO userDTO) {
        User user=UserDTO.prepareUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user1=userRepository.save(user);
        return User.prepareUserDTO(user1);
    }

    @Override
    public UserDTO deleteUser(Long id) {
        User user=userRepository.getById(id);
        userRepository.deleteById(id);
        return User.prepareUserDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            return User.prepareUserDTO(user.get());
        }else {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user=userRepository.getById(id);
        userDTO.setUserId(user.getUserId());

        //only update not null values
        Condition<?, ?> condition = Conditions.isNotNull();
        modelMapper.getConfiguration().setPropertyCondition(condition);
        modelMapper.map(userDTO,user);

        userRepository.save(user);
        return User.prepareUserDTO(user);
    }

    @Override
    public String checkUser(Long id) {
        if(userRepository.findById(id).isPresent())
            return "present";
        return null;
    }
}
