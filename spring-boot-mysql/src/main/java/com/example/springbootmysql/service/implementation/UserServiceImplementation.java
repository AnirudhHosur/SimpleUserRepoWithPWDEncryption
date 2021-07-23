package com.example.springbootmysql.service.implementation;

import com.example.springbootmysql.model.dto.request.CreateUserRequest;
import com.example.springbootmysql.model.dto.response.UserResponse;
import com.example.springbootmysql.model.entity.User;
import com.example.springbootmysql.repository.UserRepository;
import com.example.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Boolean createUser(List<CreateUserRequest> createUserRequests) {
        try{
            List<User> users = new ArrayList<>();
            createUserRequests.forEach(createUserRequest -> {
                User user =User.builder()
                        .email(createUserRequest.getEmail())
                        .firstName(createUserRequest.getFirstName())
                        .lastName(createUserRequest.getLastName())
                        // HERE THE PASSWORD ENCODING TAKES PLACE
                        .password(passwordEncoder().encode(createUserRequest.getPassword()))
                        .build();
                users.add(user);
                users.forEach(user1 -> {
                    userRepository.save(user1);
                });
            });
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        try{
            List<User> users =userRepository.findAll();
            List<UserResponse> responses = new ArrayList<>();
            for(int i=0; i< users.size(); i++){
                UserResponse userResponse = UserResponse.builder()
                        .email(users.get(i).getEmail())
                        .firstName(users.get(i).getFirstName())
                        .lastName(users.get(i).getLastName())
                        .password(users.get(i).getPassword())
                        .build();
                responses.add(userResponse);
            }
            return responses;
        }
        catch (Exception e){
            return null;
        }
    }
}
