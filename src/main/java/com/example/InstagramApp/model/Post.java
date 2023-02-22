package com.example.InstagramApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name="tbl_post")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="update_date")
    private Timestamp updateDate;
    @Column(name="pot_data")
     private String postData;
    @JoinColumn (name="user_id")
    @ManyToOne
     private User user;
}
