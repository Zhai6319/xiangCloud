package com.xiang.cloud.sys.service.impl;

import com.xiang.base.model.Params;
import com.xiang.cloud.sys.dao.SysMenuDao;
import com.xiang.cloud.sys.dto.SysMenuDTO;
import com.xiang.cloud.sys.service.SysMenuService;
import com.xiang.base.service.impl.BaseServiceImpl;
import com.xiang.cloud.sys.model.SysMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhaijianchao
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenuDTO> queryByUserId(Long userId) {
        Params params = Params.create();
        SysMenuDTO sysMenuDTO = new SysMenuDTO();
        sysMenuDTO.setUserId(userId);
        sysMenuDTO.setDeleted(false);
        params.add(sysMenuDTO);
        return sysMenuDao.selectDto(params);
    }

    @Override
    public List<SysMenuDTO> queryByUserIdAndMenuType(Long parentId,Long userId, String type, String platform) {
        Params params = Params.create();
        SysMenuDTO sysMenuDTO = new SysMenuDTO();
        sysMenuDTO.setUserId(userId);
        sysMenuDTO.setType(type);
        sysMenuDTO.setPlatform(platform);
        sysMenuDTO.setParentId(parentId);
        params.add(sysMenuDTO);
        return sysMenuDao.selectDto(params);
    }
}
