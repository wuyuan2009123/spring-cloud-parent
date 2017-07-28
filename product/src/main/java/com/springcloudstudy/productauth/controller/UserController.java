package com.springcloudstudy.productauth.controller;

import com.springcloudstudy.productauth.dao.UserRepositoty;
import com.springcloudstudy.productauth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wuy on 2017/7/26.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepositoty userRepositoty;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return this.userRepositoty.findOne(id);
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        return this.userRepositoty.findOne(user.getId());
    }
}
