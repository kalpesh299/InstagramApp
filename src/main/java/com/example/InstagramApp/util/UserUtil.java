package com.example.InstagramApp.util;

import com.example.InstagramApp.model.User;
import org.json.JSONObject;

public class UserUtil {

    public static User setUser(JSONObject json){
        User user=new User();
        user.setUserName(json.getString("userName"));
        user.setAge(json.getInt("age"));
        user.setLastName(json.getString("lastName"));
        user.setEmail(json.getString("email"));
        user.setPhoneNumber(json.getString("phoneNumber"));

        return user;
    };

}
