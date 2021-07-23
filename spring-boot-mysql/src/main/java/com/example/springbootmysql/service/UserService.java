package com.example.springbootmysql.service;


import com.example.springbootmysql.model.dto.request.CreateUserRequest;
import com.example.springbootmysql.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    Boolean createUser(List<CreateUserRequest> createUserRequests);

    List<UserResponse> getAllUsers();

}
