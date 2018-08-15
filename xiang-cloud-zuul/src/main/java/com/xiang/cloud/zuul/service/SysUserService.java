package com.xiang.cloud.zuul.service;

import com.xiang.base.service.BaseService;
import com.xiang.cloud.zuul.model.SysUser;

public interface SysUserService extends BaseService<SysUser> {

    SysUser getByLoginName(String userName);

}
