package com.xiang.cloud.user.controller.m.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 添加系统操作员参数接收
 * @author zhaijianchao
 * @date 2018/08/14
 */
@Data
public class SysopSaveRequest {
    @NotNull(message = "用户姓名不能为空")
    private String name;

    @NotNull(message = "用户性别不能为空")
    private String sex;

    @NotNull(message = "用户昵称不能为空")
    private String nickName;

    @NotNull(message = "用户名称不能为空")
    @Size(message = "{username.limit}", min = 6, max = 16)
    private String userName;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "手机号不能为空")
    @Pattern(message = "{mobile.format}", regexp = "^13[0-9]{9}$|(^14)[5,7]\\d{8}$|15[012356789]\\d{8}$|18[0-9]{9}$|17[0-9]{9}$|19[0-9]{9}$|16[0-9]{9}$")
    private String mobile;

    private String email;

    private String idCard;

    private String userHeadPortrait;
}
