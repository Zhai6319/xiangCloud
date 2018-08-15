package com.xiang.cloud.interceptor.config;

import com.xiang.cloud.interceptor.m.SgccLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * spring mvc拦截器配置
 * 用于配置登陆及权限验证拦截器加载
 * @author zhaijianchao
 * @date 2018-08-07
 */
@Configuration
public class SgccLoginInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor sgccLoginInterceptor(){
        return new SgccLoginInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/m/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sgccLoginInterceptor());
    }

}
