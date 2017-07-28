package com.springcloudstudy.zuul.inter;

import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.inter.impl.MovieFeignClientCallBackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consumerfeignclient",fallbackFactory = MovieFeignClientCallBackFactory.class)
public interface MovieFeignClient {
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") Long id);
}
