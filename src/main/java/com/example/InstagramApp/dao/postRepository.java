package com.example.InstagramApp.dao;

import com.example.InstagramApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Post,Integer> {
}
