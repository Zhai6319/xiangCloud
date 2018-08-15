package com.xiang.cloud.common.enums.sys;

import com.xiang.base.enums.BaseEnum;

/**
 * 所属平台
 * @date 2018-07-30
 * @author zhaijianchao
 */
public enum PlatformEnum implements BaseEnum {
    MANAGE("MANAGE","后台管理"),
    CORP("CORP","企业端"),
    WAP("WAP","手机端");

    private String code;
    private String desc;

    PlatformEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
