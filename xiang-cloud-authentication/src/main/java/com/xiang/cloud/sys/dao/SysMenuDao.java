package com.xiang.cloud.sys.dao;

import com.xiang.base.dao.BaseDao;
import com.xiang.base.model.Params;
import com.xiang.cloud.sys.dto.SysMenuDTO;
import com.xiang.cloud.sys.model.SysMenu;

import java.util.List;

/**
 * @author zhaijianchao
 */
public interface SysMenuDao extends BaseDao<SysMenu> {

    /**
     * 连表查询系统权限
     * @param params
     * @return
     * */
    List<SysMenuDTO> selectDto(Params params);
}