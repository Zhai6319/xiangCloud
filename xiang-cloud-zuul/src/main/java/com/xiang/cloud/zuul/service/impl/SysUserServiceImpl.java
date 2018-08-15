package com.xiang.cloud.zuul.service.impl;

import com.xiang.base.service.impl.BaseServiceImpl;
import com.xiang.cloud.zuul.dao.SysUserDao;
import com.xiang.cloud.zuul.model.SysUser;
import com.xiang.cloud.zuul.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaijianchao
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    private SysUserDao sysUserMapper;

    @Override
    public SysUser getByLoginName(String userName) {
        return null;
    }
}
