package com.xiang.cloud.user.controller.m.response;


import lombok.Data;

/**
 * 系统操作员
 * @author zhaijianchao
 */
@Data
public class SysopQueryResponse {
    private String id;
    private String name;
    private String sex;
    private String nickName;
    private String userName;
    private String mobile;
    private String email;
    private Integer loginCount;
    private String idCard;
    private String userStatus;
    private String userHeadPortrait;

}
