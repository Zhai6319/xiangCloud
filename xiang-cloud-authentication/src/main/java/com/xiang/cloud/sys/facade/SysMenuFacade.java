package com.xiang.cloud.sys.facade;

import com.xiang.base.facade.BaseFacade;
import com.xiang.cloud.sys.vo.SysMenuVo;

import java.util.List;

/**
 * @author zhaijianchao
 */
public interface SysMenuFacade extends BaseFacade {

    /**
     * 获取用户权限
     * @return
     * */
    List<SysMenuVo> getUserMenu();
    /**
     * 获取列表权限
     * @param parentId 父权限ID
     * @param type 权限类型
     * @param platform 权限所属平台
     * @return 返回列表
     * */
    List<SysMenuVo> getUserListMenu(Long parentId,String type,String platform);


}
