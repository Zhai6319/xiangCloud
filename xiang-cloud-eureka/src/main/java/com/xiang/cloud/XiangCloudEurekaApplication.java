package com.xiang.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhaijianchao
 */
@SpringBootApplication
@EnableEurekaServer
public class XiangCloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangCloudEurekaApplication.class, args);
    }
}
