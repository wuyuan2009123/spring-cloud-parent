package com.springcloudstudy.consumerfeign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloudstudy.consumerfeign.entity.User;
import com.springcloudstudy.consumerfeign.inter.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuy on 2017/7/26.
 */
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //@HystrixCommand(fallbackMethod = "findUserByIdCallBack")
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){

        return userFeignClient.findById(id);
    }

//    public User findUserByIdCallBack( Long id){
//        User user=new User();
//        user.setId(0L);
//        user.setAge(0);
//        user.setUsername("默认用户");
//        return user;
//    }





    @GetMapping("/post/{id}")
    public User postUser(@PathVariable Long id){
        User user=new User();
        user.setId(id);
        return userFeignClient.postUser(user);
    }

    @GetMapping("/logs")
    public void log(){
        ServiceInstance instance= this.loadBalancerClient.choose("PRODUCT");
        System.out.println(instance.getServiceId()+" ,host :"+instance.getHost()+" , port:"+instance.getPort());
    }
}
