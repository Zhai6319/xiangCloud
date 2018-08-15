package com.xiang.cloud.common.exception;

/**
 * 业务异常
 * created by zhaijianc on 2018-08-11
 * @author zhaijianchao
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 7842750120642341817L;

    public BusinessException(String message){
        super(message);
    }
}
