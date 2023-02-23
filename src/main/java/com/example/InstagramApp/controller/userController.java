package com.example.InstagramApp.controller;

import com.example.InstagramApp.model.User;
import com.example.InstagramApp.service.userService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {

    @Autowired
    userService userservice;
    @PostMapping("/user")
    public ResponseEntity<String>saveUser(@RequestBody String userbody){
        JSONObject json=new JSONObject(userbody);
        User user=setUser(json);
        int userid=userservice.saveUser(user);
        return new ResponseEntity("user saved with id "+ userid, HttpStatus.CREATED);
    }
    public User setUser(JSONObject json){
        User user=new User();
        user.setUserName(json.getString("userName"));
        user.setAge(json.getInt("age"));
        user.setLastName(json.getString("lastName"));
        user.setEmail(json.getString("email"));
        user.setPhoneNumber(json.getString("phoneNumber"));

        return user;
    };

    @GetMapping("/getuser")
    public ResponseEntity <String>getUser(@Nullable @RequestParam String userid){
            JSONArray userdetails=userservice.getUser(userid);
            return new ResponseEntity(userdetails.toString(),HttpStatus.OK);
    }

    @PutMapping("/user/{userid}")
    public ResponseEntity<String>updateUser(@PathVariable String userid,@RequestBody String user){
        JSONObject jsonuser=new JSONObject(user);
        User updateduser=setUser(jsonuser);
        userservice.updateUser(userid,updateduser);
        return new ResponseEntity("dataupdated",HttpStatus.CREATED);
    }

    @DeleteMapping("deleteuser")
    public ResponseEntity<String>deleteUser(@RequestParam String id){
        Integer userid=Integer.valueOf(id);
        userservice.deleteUser(userid);
        return new ResponseEntity<>("User deleted with ID "+id,HttpStatus.OK );
    }

}
