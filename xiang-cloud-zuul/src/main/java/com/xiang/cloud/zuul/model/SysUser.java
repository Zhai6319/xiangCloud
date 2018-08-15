package com.xiang.cloud.zuul.model;

import com.xiang.base.model.BaseModel;

import java.util.Date;

/**
 *  @author Zhai_
 */
public class SysUser extends BaseModel<Long>{
    private String name;
    private String sex;
    private String nickName;
    private String loginName;
    private String password;
    private String idcard;
    private String idcardZmImg;
    private String idcardFmImg;
    private String userType;
    private String userStatus;
    private Long corpId;
    private String email;
    private String mobile;
    private String ip;
    private Date loginTime;
    private Integer loginCount;
    private Integer loginErrorCount;
    private String userHeadPortrait;

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
     * 密码
     * */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     * 密码
     * */
    public String getPassword(){
        return this.password;
    }

    /**
     * 证件号码
     * */
    public void setIdcard(String idcard){
        this.idcard = idcard;
    }
    /**
     * 证件号码
     * */
    public String getIdcard(){
        return this.idcard;
    }

    /**
     * 证件正面照
     * */
    public void setIdcardZmImg(String idcardZmImg){
        this.idcardZmImg = idcardZmImg;
    }
    /**
     * 证件正面照
     * */
    public String getIdcardZmImg(){
        return this.idcardZmImg;
    }

    /**
     * 证件反面照
     * */
    public void setIdcardFmImg(String idcardFmImg){
        this.idcardFmImg = idcardFmImg;
    }
    /**
     * 证件反面照
     * */
    public String getIdcardFmImg(){
        return this.idcardFmImg;
    }

    /**
     * 用户类型 MANAGE:管理员，CORP:企业，STORE:店铺，MEMBER:会员
     * */
    public void setUserType(String userType){
        this.userType = userType;
    }
    /**
     * 用户类型 MANAGE:管理员，CORP:企业，STORE:店铺，MEMBER:会员
     * */
    public String getUserType(){
        return this.userType;
    }

    /**
     * 用户状态 NORMAL：正常，LOCK：锁定，APPLYLOGOUT：申请注销，LOGOUT：注销
     * */
    public void setUserStatus(String userStatus){
        this.userStatus = userStatus;
    }
    /**
     * 用户状态 NORMAL：正常，LOCK：锁定，APPLYLOGOUT：申请注销，LOGOUT：注销
     * */
    public String getUserStatus(){
        return this.userStatus;
    }

    /**
     * 所属企业ID
     * */
    public void setCorpId(Long corpId){
        this.corpId = corpId;
    }
    /**
     * 所属企业ID
     * */
    public Long getCorpId(){
        return this.corpId;
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
     * 最后登陆IP
     * */
    public void setIp(String ip){
        this.ip = ip;
    }
    /**
     * 最后登陆IP
     * */
    public String getIp(){
        return this.ip;
    }

    /**
     * 最后登陆时间
     * */
    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }
    /**
     * 最后登陆时间
     * */
    public Date getLoginTime(){
        return this.loginTime;
    }

    /**
     * 累计登陆次数
     * */
    public void setLoginCount(Integer loginCount){
        this.loginCount = loginCount;
    }
    /**
     * 累计登陆次数
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