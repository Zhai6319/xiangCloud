package com.xiang.cloud.common.exception.handler;

import com.xiang.base.response.R;
import com.xiang.cloud.common.exception.BusinessException;
import com.xiang.cloud.common.exception.LoginException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;

/**
 * 全局异常除处理
 * @author zhaijianchao
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * 登陆异常处理
     * */
    @ExceptionHandler(value = LoginException.class)
    public R jsonLoginErrorHandler(HttpServletRequest req, LoginException e) {
        R r = R.error(403,e.getMessage());
        r.put("url",req.getRequestURL().toString());
        logger.error(e.getMessage());
        return r;
    }

    /**
     * 登陆异常处理
     * */
    @ExceptionHandler(value = BusinessException.class)
    public R jsonBusinessErrorHandler(HttpServletRequest req, BusinessException e) {
        R r = R.error(401,e.getMessage());
        r.put("url",req.getRequestURL().toString());
        logger.error(e.getMessage());
        return r;
    }
    /**
     * 参数验证异常处理
     * */
    @ExceptionHandler(value = BindException.class)
    public R jsonValidationErrorHandler(HttpServletRequest request, BindException e){
        R r = R.error(500,e.getFieldError().getDefaultMessage());
        r.put("url",request.getRequestURL().toString());
        logger.error(e.getMessage());
        return r;
    }

    /**
     * 所有异常处理
     * */
    @ExceptionHandler(value = Exception.class)
    public R jsonAllErrorHandler(HttpServletRequest request, Exception e) {
        R r = R.error(e.hashCode(),e.getMessage());
        r.put("url",request.getRequestURL().toString());
        logger.error(e.getMessage());
        e.printStackTrace();
        return r;
    }

}
