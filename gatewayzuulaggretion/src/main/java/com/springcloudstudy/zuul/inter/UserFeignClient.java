package com.springcloudstudy.zuul.inter;

import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.inter.impl.UserFeignClientCallBackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product",fallbackFactory= UserFeignClientCallBackFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
