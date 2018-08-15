package com.xiang.cloud.sys.dao;

import com.xiang.base.dao.BaseDao;
import com.xiang.cloud.sys.model.SysRole;

import java.util.List;

/**
 * @author zhaijianchao
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 根据用户ID查询用户绑定角色
     * @param userId
     * @return
     * */
    List<SysRole> selectByUserId(Long userId);
}