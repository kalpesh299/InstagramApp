package com.example.InstagramApp.service;

import com.example.InstagramApp.dao.userRepository;
import com.example.InstagramApp.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    userRepository userrepository;
    public int saveUser(User requestuser){
        User user=userrepository.save(requestuser);
        return user.getUserId();
    }

    public JSONArray getUser(String id){
         JSONArray userArray=new JSONArray();
        if( id!=null){
            User user=userrepository.findById(Integer.valueOf(id)).get();
           if(user!=null){
               JSONObject userObj=setUser(user);
               userArray.put(userObj);
           }


        }else{
             List<User> userList=userrepository.findAll();
             for(User user:userList){
                 JSONObject userobject=setUser(user);
                 userArray.put(userobject);
             }
         }
         return userArray;
    }

    private JSONObject setUser(User user){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("userName",user.getUserName());
        jsonObject.put("lastName",user.getUserName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNumber",user.getPhoneNumber());

        return jsonObject;
    }
    public void updateUser(String id,User newuser){
//        if(userrepository.findById(Integer.valueOf(id)).isPresent()){
//            User user=userrepository.findById(Integer.valueOf(id)).get();
//            user.setUserName(newuser.getUserName());
//            user.setPhoneNumber(newuser.getPhoneNumber());
//            user.setEmail(newuser.getEmail());
//            user.setAge(newuser.getAge());
//            user.setLastName(newuser.getLastName());
//            userrepository.save(user);
//        }

        if(userrepository.findById(Integer.valueOf(id)).isPresent()){
            User user=userrepository.findById(Integer.valueOf(id)).get();
            userrepository.save(user);
        }

    }

    public void deleteUser(Integer id){
        if(userrepository.findById(id).isPresent()){
            userrepository.deleteById(id);
        }
    }
}
