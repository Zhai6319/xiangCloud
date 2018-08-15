package com.xiang.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhaijianchao
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.xiang.cloud.**.dao")
public class XiangCloudControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangCloudControlApplication.class, args);
    }
}
