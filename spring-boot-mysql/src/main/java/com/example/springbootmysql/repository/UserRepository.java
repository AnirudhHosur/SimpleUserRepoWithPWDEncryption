package com.example.springbootmysql.repository;

import com.example.springbootmysql.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
