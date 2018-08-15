package com.xiang.cloud.annotation.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要登录验证的Controller的方法上使用此注解
 * @author zhaijianchao
 * @date 2018-08-07
 */
@Target({ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface MLogin {
    /**
     * 权限验证url
     * 用于判断用户是否拥有该权限
     * 默认空字符串,代表只验证是否登陆，不做权限验证
     * */
    String value() default "";

}
