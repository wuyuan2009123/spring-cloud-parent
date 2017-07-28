package com.springcloudstudy.consumerfeign.controller;

import com.springcloudstudy.consumerfeign.entity.User;
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
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${user.serviceUrl}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        System.out.println(this.userServiceUrl);
        return restTemplate.getForObject(userServiceUrl+id,User.class);
    }

    @GetMapping("/logs")
    public void log(){
        ServiceInstance instance= this.loadBalancerClient.choose("PRODUCT");
        System.out.println(instance.getServiceId()+" ,host :"+instance.getHost()+" , port:"+instance.getPort());
    }
}
