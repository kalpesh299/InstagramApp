package com.example.InstagramApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//to auto increment primary key
    private int userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="age")
    private Integer age;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;

}
