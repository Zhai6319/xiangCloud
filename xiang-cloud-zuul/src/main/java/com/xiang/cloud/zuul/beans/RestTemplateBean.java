package com.xiang.cloud.zuul.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaijianchao
 */
@Configuration
public class RestTemplateBean {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
