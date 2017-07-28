//package com.springcloudstudy.consumerfeign.inter.impl;
//
//import com.springcloudstudy.consumerfeign.entity.User;
//import com.springcloudstudy.consumerfeign.inter.UserFeignClient;
//import org.springframework.stereotype.Component;
//
//
///**
// * Created by wuy on 2017/7/26.
// */
//@Component
//public class UserFeignClientCallBack implements UserFeignClient {
//    @Override
//    public User findById(Long id) {
//        User user=new User();
//        user.setUsername("findById 默认用户");
//        return user;
//    }
//
//    @Override
//    public User postUser(User user) {
//        user=new User();
//        user.setUsername("postUser 默认用户");
//        return user;
//    }
//}
