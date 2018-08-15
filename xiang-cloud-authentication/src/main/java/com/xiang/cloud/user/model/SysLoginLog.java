package com.xiang.cloud.user.model;

import com.xiang.base.model.BaseModel;

import java.util.Date;

/**
 *  @author Zhai_
 */
public class SysLoginLog extends BaseModel<Long>{
    private static final long serialVersionUID = 4494151734657530984L;
    private Long userId;
    private String loginName;
    private Long corpId;
    private Date loginTime;
    private String loginIp;
    private String loginAddress;
    private String loginPlatform;

    /**
     * 用户id
     * */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /**
     * 用户id
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
     * 所属企业id
     * */
    public void setCorpId(Long corpId){
        this.corpId = corpId;
    }
    /**
     * 所属企业id
     * */
    public Long getCorpId(){
        return this.corpId;
    }

    /**
     * 登陆时间
     * */
    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }
    /**
     * 登陆时间
     * */
    public Date getLoginTime(){
        return this.loginTime;
    }

    /**
     * 登陆ip
     * */
    public void setLoginIp(String loginIp){
        this.loginIp = loginIp;
    }
    /**
     * 登陆ip
     * */
    public String getLoginIp(){
        return this.loginIp;
    }

    /**
     * 登陆地址
     * */
    public void setLoginAddress(String loginAddress){
        this.loginAddress = loginAddress;
    }
    /**
     * 登陆地址
     * */
    public String getLoginAddress(){
        return this.loginAddress;
    }

    /**
     * 登陆平台
     * */
    public String getLoginPlatform() {
        return loginPlatform;
    }
    /**
     * 登陆平台
     * */
    public void setLoginPlatform(String loginPlatform) {
        this.loginPlatform = loginPlatform;
    }
}