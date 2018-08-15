package com.xiang.cloud.user.service.impl;

import com.xiang.base.model.Params;
import com.xiang.base.service.impl.BaseServiceImpl;
import com.xiang.cloud.user.dao.SysUserDao;
import com.xiang.cloud.user.model.SysUser;
import com.xiang.cloud.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaijianchao
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    private SysUserDao sysUserMapper;

    @Override
    public SysUser getByLoginName(String userName) {
        Params params = Params.create();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        params.add(sysUser);
        List<SysUser> users = this.queryByParams(params);
        if(users != null && !users.isEmpty()){
            return users.get(0);
        }
        return null;
    }


    @Override
    public SysUser getByMobile(String mobile) {
        Params params = Params.create();
        SysUser sysUser = new SysUser();
        sysUser.setMobile(mobile);
        params.add(sysUser);
        List<SysUser> users = this.queryByParams(params);
        if (users != null && !users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public SysUser getByEmail(String email) {
        Params params = Params.create();
        SysUser sysUser = new SysUser();
        sysUser.setEmail(email);
        params.add(sysUser);
        List<SysUser> users = this.queryByParams(params);
        if (users != null && !users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public SysUser getByIdCard(String idCard) {
        Params params = Params.create();
        SysUser sysUser = new SysUser();
        sysUser.setIdCard(idCard);
        params.add(sysUser);
        List<SysUser> users = this.queryByParams(params);
        if (users != null && !users.isEmpty()){
            return users.get(0);
        }
        return null;
    }
}
