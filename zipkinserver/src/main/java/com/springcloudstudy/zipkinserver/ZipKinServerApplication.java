package com.springcloudstudy.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by wuy on 2017/7/28.
 */

@SpringBootApplication
@EnableZipkinServer
public class ZipKinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipKinServerApplication.class,args);
    }
}
