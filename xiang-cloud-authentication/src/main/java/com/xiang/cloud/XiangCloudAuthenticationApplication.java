package com.xiang.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhaijianchao
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.xiang.cloud.**.dao")
@EnableCaching
@EnableSwagger2
public class XiangCloudAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangCloudAuthenticationApplication.class, args);
    }
}
