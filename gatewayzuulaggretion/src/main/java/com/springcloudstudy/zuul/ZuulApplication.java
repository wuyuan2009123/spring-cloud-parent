package com.springcloudstudy.zuul;

import com.springcloudstudy.zuul.zuulfilter.PreRequestLogFilter;
import com.springcloudstudy.zuul.zuulfilter.PreRequestPostFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by wuy on 2017/7/27.
 */

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class ZuulApplication {

    @Bean
    public PreRequestPostFilter requestPostFilter(){
        return new PreRequestPostFilter();
    }

    @Bean
    public PreRequestLogFilter requestLogFilter(){
        return new PreRequestLogFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
