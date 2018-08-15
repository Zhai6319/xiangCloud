package com.xiang.cloud.user.model;

import com.xiang.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  @author Zhai_
 */
public class SysUserLogin extends BaseModel<Long>{
    private Long userId;
    private String loginName;
    private String loginMode;
    private String loginToken;

    /**
     * 所属用户id
     * */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /**
     * 所属用户id
     * */
    public Long getUserId(){
        return this.userId;
    }

    /**
     * 登陆名
     * */
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }
    /**
     * 登陆名
     * */
    public String getLoginName(){
        return this.loginName;
    }

    /**
     * 登陆方式
     * */
    public void setLoginMode(String loginMode){
        this.loginMode = loginMode;
    }
    /**
     * 登陆方式
     * */
    public String getLoginMode(){
        return this.loginMode;
    }

    /**
     * 登陆令牌-密码
     * */
    public void setLoginToken(String loginToken){
        this.loginToken = loginToken;
    }
    /**
     * 登陆令牌-密码
     * */
    public String getLoginToken(){
        return this.loginToken;
    }

}