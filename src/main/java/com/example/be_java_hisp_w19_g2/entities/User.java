package com.example.be_java_hisp_w19_g2.entities;

import com.example.be_java_hisp_w19_g2.dtos.PostWithDiscountDTO;
import com.example.be_java_hisp_w19_g2.roles.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private List<User> followed = new ArrayList<>();
    private List<User> followers = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    private List<PostWithDiscount> postWithDiscounts = new ArrayList<>();
    private Roles userRol;

    public User(Integer userId, String userName, Roles userRol) {
        this.userId = userId;
        this.userName = userName;
        this.userRol = userRol;
    }

    public void followUser(User userToFollow) {
        followed.add(userToFollow);
        userToFollow.addNewFollower(this);
    }

    public void unfollowUser(User userToUnfollow) {
        followed  = followed.stream()
                .filter(u -> !u.getUserId().equals(userToUnfollow.getUserId()))
                .collect(Collectors.toList());
        userToUnfollow.removeFollower(this);
    }

    private void addNewFollower(User newFollower) {
        followers.add(newFollower);
    }

    private void removeFollower(User oldFollowe) {
        followers  = followers.stream()
                .filter(u -> !u.getUserId().equals(oldFollowe.getUserId()))
                .collect(Collectors.toList());
    }

    public void addPost(Post newPost) {
        posts.add(newPost);
    }
    public void addPostDiscount(PostWithDiscount postWithDiscountDTO){postWithDiscounts.add(postWithDiscountDTO);}
}
