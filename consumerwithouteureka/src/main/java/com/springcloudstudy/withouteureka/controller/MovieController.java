package com.springcloudstudy.withouteureka.controller;

import com.springcloudstudy.withouteureka.entity.User;
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



    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        return restTemplate.getForObject("localhost:7900",User.class);
    }

    @GetMapping("/logs")
    public void log(){
        ServiceInstance instance= this.loadBalancerClient.choose("PRODUCT");
        System.out.println(instance.getServiceId()+" ,host :"+instance.getHost()+" , port:"+instance.getPort());
    }
}
