package com.xiang.cloud.user.service;

import com.xiang.base.service.BaseService;
import com.xiang.cloud.user.model.SysUserLogin;

import java.util.List;

/**
 * 用户登陆接口
 * @author zhaijianchao
 * @date 2018/08/15
 */
public interface SysUserLoginService extends BaseService<SysUserLogin>{

    /**
     * 根据登陆名查询用户登陆信息
     * @param loginName 登陆名
     * @return 返回用户登陆信息
     * */
    SysUserLogin getByLoginName(String loginName);


}
