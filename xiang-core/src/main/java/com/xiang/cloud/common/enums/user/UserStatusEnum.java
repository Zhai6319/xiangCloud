package com.xiang.cloud.common.enums.user;

import com.xiang.base.enums.BaseEnum;

/**
 * 用户状态枚举
 * @author zhaijianchao
 * @date
 */
public enum UserStatusEnum implements BaseEnum{
    NORMAL("NORMAL","正常"),
    LOCK("LOCK","锁定"),
    APPLYLOGOUT("APPLYLOGOUT","申请注销"),
    LOGOUT("LOGOUT","注销");


    private String code;
    private String desc;

    private UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public static UserStatusEnum getByCode(String code) {
        UserStatusEnum[] arg4;
        int arg3 = (arg4 = values()).length;

        for (int arg2 = 0; arg2 < arg3; ++arg2) {
            UserStatusEnum e = arg4[arg2];
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public String getCode() {
        return this.code;
    }
    @Override
    public String getDesc() {
        return this.desc;
    }

}
