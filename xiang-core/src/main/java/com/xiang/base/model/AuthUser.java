package com.xiang.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaijianchao
 */
@Data
public class AuthUser implements Serializable {
    private static final long serialVersionUID = -3254920427403656342L;


    public static final String UID = "uid";
    public static final String ID = "id";
    public static final String LOGIN_NAME = "loginName";
    public static final String NICK_NAME = "nickName";
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String IDCARD = "idcard";
    public static final String CORP_ID = "corpId";
    public static final String USER_TYPE = "userType";

    /**
     * uuid
     * */
    private String uid;
    /**
     * 用户ID
     * */
    private Long id;
    /**
     * 登陆名
     * */
    private String loginName;
    /**
     * 昵称
     * */
    private String nickName;
    /**
     * 真实姓名
     * */
    private String name;
    /**
     * 手机号
     * */
    private String mobile;
    /**
     * 邮箱
     * */
    private String email;
    /**
     * 身份证
     * */
    private String idcard;
    /**
     * 所属企业ID
     * */
    private Long corpId;
    /**
     * 用户类型
     * */
    private String userType;
}
