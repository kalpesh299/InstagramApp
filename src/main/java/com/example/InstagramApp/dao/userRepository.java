package com.example.InstagramApp.dao;

import com.example.InstagramApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Integer> {
}
