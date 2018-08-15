package com.xiang.cloud.common.enums.user;

import com.xiang.base.enums.BaseEnum;


/**
 * 登陆方式枚举
 * @author zhaijianchao
 * @date 2018/08/15
 */
public enum LoginModeEnum implements BaseEnum {
    LOGIN_NAME("LOGIN_NAME","登陆名密码登陆"),
    MOBILE("MOBILE","手机号登陆"),
    EMAIL("EMAIL","邮箱登陆");

    private String code;
    private String desc;

    LoginModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public LoginModeEnum getByCode(String code) {
        LoginModeEnum[] arg4;
        int arg3 = (arg4 = values()).length;

        for (int arg2 = 0; arg2 < arg3; ++arg2) {
            LoginModeEnum e = arg4[arg2];
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
