package com.springcloudstudy.zuul.inter.impl;


import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.inter.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wuy on 2017/7/26.
 */
@Component
public class UserFeignClientCallBackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserFeignClientCallBackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient(){
            @Override
            public User findById(Long id) {
                UserFeignClientCallBackFactory.LOGGER.info("fall findById back ",throwable);
                User user=new User();
                user.setUsername(" UserFeignClient findById 默认用户");
                return user;
            }
        };
    }
}
