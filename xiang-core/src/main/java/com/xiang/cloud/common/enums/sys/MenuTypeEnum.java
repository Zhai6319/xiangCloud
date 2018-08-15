package com.xiang.cloud.common.enums.sys;

import com.xiang.base.enums.BaseEnum;

/**
 * @author zhaijianchao
 * 菜单权限类型
 */
public enum MenuTypeEnum implements BaseEnum {
    /**根目录*/
    ROOT("ROOT","根目录"),
    /**分组*/
    GROUP("GROUP","分组"),
    /**菜单*/
    URL("URL","菜单"),
    /**表头按钮*/
    BUTTON_TOP("BUTTON_TOP","表头按钮"),
    /**表体按钮*/
    BUTTON_LIST("BUTTON_LIST","表体按钮");


    private String code;
    private String desc;

    private MenuTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public static MenuTypeEnum getByCode(String code) {
        MenuTypeEnum[] arg4;
        int arg3 = (arg4 = values()).length;

        for (int arg2 = 0; arg2 < arg3; ++arg2) {
            MenuTypeEnum e = arg4[arg2];
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
