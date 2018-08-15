package com.xiang.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhaijianchao
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class XiangCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangCloudConfigServerApplication.class, args);
    }
}
