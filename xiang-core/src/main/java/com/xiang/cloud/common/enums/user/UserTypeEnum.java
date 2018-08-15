package com.xiang.cloud.common.enums.user;


import com.xiang.base.enums.BaseEnum;

/**
 * @author zhaijianchao
 */

public enum UserTypeEnum implements BaseEnum {
    MANAGE("MANAGE","后台用户");


    private String code;
    private String desc;

    private UserTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public UserTypeEnum getByCode(String code) {
        UserTypeEnum[] arg4;
        int arg3 = (arg4 = values()).length;

        for (int arg2 = 0; arg2 < arg3; ++arg2) {
            UserTypeEnum e = arg4[arg2];
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
