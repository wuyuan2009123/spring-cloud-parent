package com.springcloudstudy.zuul.inter.impl;


import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.inter.MovieFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wuy on 2017/7/26.
 */
@Component
public class MovieFeignClientCallBackFactory implements FallbackFactory<MovieFeignClient> {
    private static final Logger LOGGER= LoggerFactory.getLogger(MovieFeignClientCallBackFactory.class);

    @Override
    public MovieFeignClient create(Throwable throwable) {
        return new MovieFeignClient(){
            @Override
            public User findUserById(Long id) {
                MovieFeignClientCallBackFactory.LOGGER.info("fall findById back ",throwable);
                User user=new User();
                user.setUsername("MovieFeignClient findUserById 默认用户");
                return user;
            }
        };
    }
}
