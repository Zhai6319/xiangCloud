package com.xiang.cloud.sys.service;

import com.xiang.base.service.BaseService;
import com.xiang.cloud.sys.dto.SysMenuDTO;
import com.xiang.cloud.sys.model.SysMenu;

import java.util.List;

/**
 * @author zhaijianchao
 */
public interface SysMenuService extends BaseService<SysMenu>{

    /***
     * 根据用户ID查询用户权限
     * @param userId
     * @return
     */
    List<SysMenuDTO> queryByUserId(Long userId);

    /**
     * 根据用户ID和用户类型和AppID查询权限
     * @param parentId
     * @param userId
     * @param type
     * @param platform
     * @return
     */
    List<SysMenuDTO> queryByUserIdAndMenuType(Long parentId,Long userId, String type,String platform);

}
