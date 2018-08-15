package com.xiang.cloud.sys.facade.impl;

import com.xiang.cloud.common.constants.SysConstant;
import com.xiang.cloud.common.enums.sys.MenuTypeEnum;
import com.xiang.cloud.common.enums.sys.PlatformEnum;
import com.xiang.base.core.cache.RedisHelper;
import com.xiang.base.core.http.SessionHelper;
import com.xiang.base.core.user.UserHelper;
import com.xiang.base.facade.impl.BaseFacadeImpl;
import com.xiang.base.model.AuthUser;
import com.xiang.cloud.sys.dto.SysMenuDTO;
import com.xiang.cloud.sys.facade.SysMenuFacade;
import com.xiang.cloud.sys.service.SysMenuService;
import com.xiang.cloud.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaijianchao
 * @date 2018.08.04
 */
@Service
public class SysMenuFacadeImpl extends BaseFacadeImpl implements SysMenuFacade {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private RedisHelper redisHelper;

    @Override
    public List<SysMenuVo> getUserMenu() {
        AuthUser authUser = UserHelper.getAuthUser();
        List<SysMenuVo> sysMenuVoList = (List<SysMenuVo>) SessionHelper.getSessionValue(SysConstant.LoginConstant.AUTH_USER_MENU+authUser.getUid());
        if(sysMenuVoList != null && !sysMenuVoList.isEmpty()){
            return sysMenuVoList;
        }
        List<SysMenuVo> roots = new ArrayList<>();
        List<SysMenuDTO> rootList = sysMenuService.queryByUserIdAndMenuType(0L,authUser.getId(), MenuTypeEnum.ROOT.getCode(),PlatformEnum.MANAGE.getCode());
        if(rootList != null && !rootList.isEmpty()){
            //获取根权限组添加到权限视图中
            rootList.forEach(root->{
                SysMenuVo rootVo = new SysMenuVo();
                rootVo.setTitle(root.getTitle());
                rootVo.setIcon(root.getStyle());
                rootVo.setName(root.getName());
                List<SysMenuVo> groups = new ArrayList<>();
                //根据根权限组获取二级菜单组
                List<SysMenuDTO> groupList = sysMenuService.queryByUserIdAndMenuType(root.getId(),authUser.getId(),MenuTypeEnum.GROUP.getCode(),PlatformEnum.MANAGE.getCode());
                groupList.forEach(group->{
                    SysMenuVo groupVo = new SysMenuVo();
                    groupVo.setName(group.getName());
                    groupVo.setTitle(group.getTitle());
                    groupVo.setIcon(group.getStyle());
                    List<SysMenuVo> menus = new ArrayList<>();
                    //根据二级菜单组ID获取菜单页url
                    List<SysMenuDTO> menuList = sysMenuService.queryByUserIdAndMenuType(group.getId(),authUser.getId(),MenuTypeEnum.URL.getCode(),PlatformEnum.MANAGE.getCode());
                    menuList.forEach(url->{
                        SysMenuVo menuVo = new SysMenuVo();
                        menuVo.setIcon(url.getStyle());
                        menuVo.setTitle(url.getTitle());
                        menuVo.setName(url.getName());
                        menuVo.setJump(url.getUrl()+"/uid="+url.getId());
                        menus.add(menuVo);
                    });
                    groupVo.setList(menus);
                    groups.add(groupVo);
                });
                rootVo.setList(groups);
                roots.add(rootVo);
            });
        }
        SessionHelper.putSession(SysConstant.LoginConstant.AUTH_USER_MENU+authUser.getUid(),roots);
        return roots;
    }

    @Override
    public List<SysMenuVo> getUserListMenu(Long parentId,String type,String platform) {
        AuthUser authUser = UserHelper.getAuthUser();
        List<SysMenuDTO>sysMenuDTOList = sysMenuService.queryByUserIdAndMenuType(parentId,authUser.getId(),type,platform);
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();
        sysMenuDTOList.forEach(sysMenuDTO -> {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setJump(sysMenuDTO.getUrl());
            sysMenuVo.setIcon(sysMenuDTO.getStyle());
            sysMenuVo.setName(sysMenuDTO.getName());
            sysMenuVo.setTitle(sysMenuDTO.getTitle());
            sysMenuVoList.add(sysMenuVo);
        });
        if (sysMenuVoList != null && !sysMenuVoList.isEmpty()) {
            return sysMenuVoList;
        }
        return null;
    }
}
