package com.example.InstagramApp.service;

import com.example.InstagramApp.dao.postRepository;
import com.example.InstagramApp.model.Post;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControllerService {

    @Autowired
    postRepository postrepository;

    public Integer savePost(Post post){
        Post newpost=postrepository.save(post);
        return newpost.getPostId();
    }
    public JSONArray getPost(Integer userId,String postId){
          JSONArray post

    }
}
