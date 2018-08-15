package com.xiang.cloud.user.controller.m.request;

import lombok.Data;

/**
 * 管理系统操作员列表查询请求类请求类
 * created by zhaijianchao on 2018-08-11
 * @author zhaijianchao
 * */
@Data
public class SysopQueryRequest {
    private String name;
    private String nickName;
    private String loginName;
    private String mobile;
    private String email;
    private String userStatus;
}
