package com.springcloudstudy.productauth.controller;

import com.springcloudstudy.productauth.dao.UserRepositoty;
import com.springcloudstudy.productauth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by wuy on 2017/7/26.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepositoty userRepositoty;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails=(UserDetails)principal;
            Collection<? extends GrantedAuthority> authorities= userDetails.getAuthorities();
            for(GrantedAuthority c:authorities){
                System.out.println(userDetails.getUsername()+" , role : "+c.getAuthority());
            }
        }
        else{
            System.out.println(".............");
        }
        return this.userRepositoty.findOne(id);
    }
}
