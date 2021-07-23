package com.example.springbootmysql.controller;


import com.example.springbootmysql.model.dto.request.CreateUserRequest;
import com.example.springbootmysql.model.dto.response.UserResponse;
import com.example.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/saveUsers")
    Boolean saveToDb(@RequestBody List<CreateUserRequest> createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping(value = "/getAllUsers")
    public List<UserResponse> listUsers(){
        return userService.getAllUsers();
    }
}
