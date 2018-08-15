package com.xiang.cloud.user.service;

import com.xiang.base.service.BaseService;
import com.xiang.cloud.user.model.SysUser;

/**
 * 用户service接口
 * @author zhaijianchao
 * @date 2018/05/01
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     * */
    SysUser getByLoginName(String userName);

    /**
     * 根据手机号查询用户
     * @param mobile 手机号
     * @return 返回用户信息
     * */
    SysUser getByMobile(String mobile);

    /**
     * 根据邮箱查询用户
     * @param email 用户邮箱
     * @return 返回用户信息
     * */
    SysUser getByEmail(String email);

    /**
     * 根据身份证号码查询用户信息
     * @param idCard 身份证号
     * @return 返回用户信息
     * */
    SysUser getByIdCard(String idCard);
}
