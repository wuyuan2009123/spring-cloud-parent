package com.springcloudstudy.slidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * Created by wuy on 2017/7/27.
 */
@SpringBootApplication
@EnableSidecar
public class SideCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SideCarApplication.class,args);
    }
}
