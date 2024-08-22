package com.bank_Project.bank_app.controller;

import com.bank_Project.bank_app.ApiResponse;
import com.bank_Project.bank_app.DTO.UserDTO;
import com.bank_Project.bank_app.service.Impl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    userServiceImpl userService;

    @PostMapping(consumes="application/json",path="/addUser")
    public ResponseEntity<ApiResponse<UserDTO>> addUser(@RequestBody UserDTO userDTO){
        UserDTO userDTO1=userService.insertUser(userDTO);
        ApiResponse<UserDTO> apiResponse=new ApiResponse<>("User created successfully.",200,userDTO1);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping(produces="application/json",path="/getUser")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@RequestParam("id") int id){
        UserDTO userDTO=userService.getUserById(id);
        if(userDTO!=null){
            ApiResponse<UserDTO> apiResponse=new ApiResponse<>("User found successfully.",200,userDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse<UserDTO> apiResponse=new ApiResponse<>("User not found.",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(produces="application/json",path="/deleteUser")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@RequestParam("Id") int id){
        ApiResponse response;
        if(userService.checkUser(id)!=null) {
            UserDTO userDTO=userService.deleteUser(id);
            response = new ApiResponse<>("User deleted successfully.", 200, userDTO);
        }else{
            response=new ApiResponse<>(("User not found with id."+id),404,null);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json",path="/updateUser")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@RequestParam("Id") int id,@RequestBody UserDTO userDTO){
        if(userService.checkUser(id)!= null) {
            UserDTO updatedUser=userService.updateUser(id,userDTO);
            ApiResponse<UserDTO> response = new ApiResponse<>("User updated successfully.", 200, updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            ApiResponse<UserDTO> apiResponse=new ApiResponse<>("User not found.",404,null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }
}
