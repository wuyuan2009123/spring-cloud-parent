package com.springcloudstudy.consumerfeign.inter.impl;

import com.springcloudstudy.consumerfeign.entity.User;
import com.springcloudstudy.consumerfeign.inter.UserFeignClient;
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
                user.setUsername("findById 默认用户");
                return user;
            }

            @Override
            public User postUser(User user) {
                UserFeignClientCallBackFactory.LOGGER.info("fall postUser back ",throwable);
                user=new User();
                user.setUsername("postUser 默认用户");
                return user;
            }
        };
    }
}
