package com.example.InstagramApp.service;

import com.example.InstagramApp.dao.postRepository;
import com.example.InstagramApp.model.Post;
import com.example.InstagramApp.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.List;

@Service
public class postService {

    @Autowired
    postRepository postrepository;

    public Integer savePost(Post post){
        Post newpost=postrepository.save(post);
        return newpost.getPostId();
    }


    public JSONArray getPost(Integer userId, String postId){
          JSONArray postarr=new JSONArray();

          if(postId!=null && postrepository.findById(Integer.valueOf(postId)).isPresent()){
            Post post=postrepository.findById(Integer.valueOf(postId)).get();
            JSONObject jsonObject=setObject(post);
            postarr.put(jsonObject);
          } else{
              List<Post>postList=postrepository.findAll();
              for(Post post:postList){
                 if(post.getUser().getUserId()==userId && post.getPostId()==Integer.valueOf(postId)) {
                     JSONObject jsonobj=setObject(post);
                     postarr.put(jsonobj);
                 }else{
                     JSONObject jsonobj=setObject(post);
                     postarr.put(jsonobj);
                 }

              }

          }
          return postarr;
    }

    public void updatePost(String id,Post updatedpost){

        if(postrepository.findById(Integer.valueOf(id)).isPresent()){
            Post olderpost=postrepository.findById(Integer.valueOf(id)).get();
            updatedpost.setPostId(olderpost.getPostId());
            updatedpost.setUser(olderpost.getUser());
            updatedpost.setCreatedDate(olderpost.getCreatedDate());
            Timestamp updatedDate=new Timestamp(System.currentTimeMillis());
            updatedpost.setUpdateDate(updatedDate);
            postrepository.save(updatedpost);
        }

    }

    public JSONObject setObject(Post post){
        JSONObject newjsonObject=new JSONObject();
        newjsonObject.put("postId",post.getPostId());
        newjsonObject.put("createdDate",post.getCreatedDate());
        newjsonObject.put("updateDate",post.getUpdateDate());
        newjsonObject.put("postData",post.getPostData());

        JSONObject userObject=new JSONObject();
        User user=post.getUser();
        userObject.put("userId", user.getUserId());
        userObject.put("userName",user.getUserName());
        userObject.put("age",user.getAge());

        newjsonObject.put("user",userObject);

        return newjsonObject;
    }

}
