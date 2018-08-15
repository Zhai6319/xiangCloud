package com.xiang.cloud.user.facade;

import com.xiang.base.core.AccessToken;
import com.xiang.cloud.user.dto.SysUserDTO;
import com.xiang.cloud.user.model.SysUser;

import java.io.UnsupportedEncodingException;

/**
 * @author zhaijianchao
 * 系统用户业务拓展层接口暴漏
 */
public interface SysUserFacade {

    /**
     * 通过用户名密码做登陆操作，系统后台登陆操作
     * @param loginName 登陆名
     * @param password 密码
     * @return AccessToken
     * @throws UnsupportedEncodingException
     * */
    AccessToken doMLogin(String loginName, String password) throws UnsupportedEncodingException;
    /**
     * 创建系统操作员
     * @param sysUser 系统用户
     * */
    void doCreateSysop(SysUserDTO sysUser);
}
