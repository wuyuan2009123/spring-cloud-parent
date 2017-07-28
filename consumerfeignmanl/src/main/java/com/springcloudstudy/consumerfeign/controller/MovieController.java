package com.springcloudstudy.consumerfeign.controller;

import com.springcloudstudy.consumerfeign.entity.User;
import com.springcloudstudy.consumerfeign.inter.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuy on 2017/7/26.
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {

    private UserFeignClient userUserFeignClient;

    private UserFeignClient userAdminFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userUserFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","pwd")).target(UserFeignClient.class,"http://product/");
        this.userAdminFeignClient=Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","pwd")).target(UserFeignClient.class,"http://product/");
    }



//    @Autowired
//    private UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/admin/{id}")
    public User findAdminById(@PathVariable Long id){
        return userAdminFeignClient.findById(id);
    }

    @GetMapping("/logs")
    public void log(){
        ServiceInstance instance= this.loadBalancerClient.choose("PRODUCT");
        System.out.println(instance.getServiceId()+" ,host :"+instance.getHost()+" , port:"+instance.getPort());
    }
}
