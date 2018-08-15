package com.xiang.cloud.user.dto;

import com.xiang.cloud.user.model.SysUser;
import lombok.Data;

/**
 * 系统用户数据传输
 * @author zhaijianchao
 * @date 2018/08/15
 */
@Data
public class SysUserDTO extends SysUser {
    private String loginName;
    private String loginMode;
    private String loginToken;

}
