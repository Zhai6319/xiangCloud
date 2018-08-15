package com.xiang.cloud.user.service.impl;

import com.xiang.base.model.Params;
import com.xiang.cloud.user.service.SysUserLoginService;
import com.xiang.base.service.impl.BaseServiceImpl;
import com.xiang.cloud.user.model.SysUserLogin;

import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户登陆业务实现
 * @author zhaijianchao
 * @date 2018/08/15
 */
@Service
public class SysUserLoginServiceImpl extends BaseServiceImpl<SysUserLogin> implements SysUserLoginService {

    @Override
    public SysUserLogin getByLoginName(String loginName) {
        SysUserLogin sysUserLogin = new SysUserLogin();
        sysUserLogin.setLoginName(loginName);
        Params params = Params.create();
        params.add(sysUserLogin);
        List<SysUserLogin> sysUserLoginList = this.queryByParams(params);
        if (sysUserLoginList != null && !sysUserLoginList.isEmpty()) {
            return sysUserLoginList.get(0);
        }
        return null;
    }
}
