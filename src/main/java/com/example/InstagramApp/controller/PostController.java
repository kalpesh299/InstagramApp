package com.example.InstagramApp.controller;

import com.example.InstagramApp.dao.userRepository;
import com.example.InstagramApp.model.Post;
import com.example.InstagramApp.model.User;
import com.example.InstagramApp.service.ControllerService;
import com.example.InstagramApp.service.userService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PostController {
    @Autowired
    ControllerService controllerService;

    @Autowired
    userRepository userrepository;

    @PostMapping("addpost")
    public ResponseEntity<String>addPost(@RequestBody String post){

        Post userpost=setPost(post);
       int postid= controllerService.savePost(userpost);
      return new ResponseEntity<>("Post added with id "+postid, HttpStatus.CREATED);
    }

    public Post setPost(String post){
        JSONObject jsonObject=new JSONObject(post);
        User user=null;
        int userId=jsonObject.getInt("user");
        if(userrepository.findById(userId).isPresent()){
            user=userrepository.findById(userId).get();
        }else{
            return null;
        }

        Post userpost=new Post();
        Timestamp createdtime=new Timestamp(System.currentTimeMillis());
        userpost.setCreatedDate(createdtime);
        Timestamp updatetime=new Timestamp(System.currentTimeMillis());
        userpost.setUpdateDate(updatetime);
        userpost.setPostData(jsonObject.getString("postData"));
      //  User user=userrepository.findById(jsonObject.getInt("user")).get();
       userpost.setUser(user);
        return userpost;

    }

    @GetMapping("getpost")
    public ResponseEntity<String>getPost(@RequestParam String userId,@Nullable @RequestParam String postId){
        JSONArray posyArr=controllerService.getPost(Integer.valueOf(userId),postId);
        return new ResponseEntity<>("pot as been fetched",HttpStatus.FOUND);
    }

}
