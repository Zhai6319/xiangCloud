package com.xiang.cloud.user.model;

import com.xiang.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Zhai_
 * @date 2018/08/14
 */
public class SysUser extends BaseModel<Long>{
    private static final long serialVersionUID = -2117351939904758563L;
    private Long corpId;
    private String userName;
    private String name;
    private String sex;
    private String nickName;
    private String mobile;
    private String email;
    private Integer loginCount;
    private Integer loginErrorCount;
    private String idCard;
    private String userType;
    private String userStatus;
    private String userHeadPortrait;

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
     * 用户名
     * */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 真实姓名
     * */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 真实姓名
     * */
    public String getName(){
        return this.name;
    }

    /**
     * 性别
     * */
    public void setSex(String sex){
        this.sex = sex;
    }
    /**
     * 性别
     * */
    public String getSex(){
        return this.sex;
    }

    /**
     * 昵称
     * */
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    /**
     * 昵称
     * */
    public String getNickName(){
        return this.nickName;
    }


    /**
     * 手机号
     * */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    /**
     * 手机号
     * */
    public String getMobile(){
        return this.mobile;
    }

    /**
     * 邮箱
     * */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * 邮箱
     * */
    public String getEmail(){
        return this.email;
    }

    /**
     * 登陆次数
     * */
    public void setLoginCount(Integer loginCount){
        this.loginCount = loginCount;
    }
    /**
     * 登陆次数
     * */
    public Integer getLoginCount(){
        return this.loginCount;
    }

    /**
     * 登陆错误次数
     * */
    public void setLoginErrorCount(Integer loginErrorCount){
        this.loginErrorCount = loginErrorCount;
    }
    /**
     * 登陆错误次数
     * */
    public Integer getLoginErrorCount(){
        return this.loginErrorCount;
    }

    /**
     * 证件号码
     * */
    public void setIdCard(String idCard){
        this.idCard = idCard;
    }
    /**
     * 证件号码
     * */
    public String getIdCard(){
        return this.idCard;
    }

    /**
     * 用户类型
     * */
    public void setUserType(String userType){
        this.userType = userType;
    }
    /**
     * 用户类型
     * */
    public String getUserType(){
        return this.userType;
    }

    /**
     * 用户状态
     * */
    public void setUserStatus(String userStatus){
        this.userStatus = userStatus;
    }
    /**
     * 用户状态
     * */
    public String getUserStatus(){
        return this.userStatus;
    }

    /**
     * 用户头像
     * */
    public void setUserHeadPortrait(String userHeadPortrait){
        this.userHeadPortrait = userHeadPortrait;
    }
    /**
     * 用户头像
     * */
    public String getUserHeadPortrait(){
        return this.userHeadPortrait;
    }

}