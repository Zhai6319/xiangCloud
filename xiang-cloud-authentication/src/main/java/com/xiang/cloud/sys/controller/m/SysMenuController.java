package com.xiang.cloud.sys.controller.m;

import com.xiang.cloud.common.enums.sys.MenuTypeEnum;
import com.xiang.cloud.common.enums.sys.PlatformEnum;
import com.xiang.base.controller.BaseController;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import com.xiang.cloud.annotation.login.MLogin;
import com.xiang.cloud.sys.facade.SysMenuFacade;
import com.xiang.cloud.sys.model.SysMenu;
import com.xiang.cloud.sys.service.SysMenuService;
import com.xiang.cloud.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统后台菜单操作
 * @author zhaijianchao
 *
 * */
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("m/sysMenu")
@Controller
public class SysMenuController extends BaseController{
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysMenuFacade sysMenuFacade;

    @ResponseBody
    @RequestMapping("page.do")
    public R page(SysMenu sysMenu){
        Params params = Params.create();
        params.add(sysMenu);
        Page page = this.getPage();
        PagedResult<SysMenu> pages = sysMenuService.page(params,page);
        return R.success().put("pageData",pages);
    }

    @ResponseBody
    @RequestMapping("detail.do")
        public R detail(Long id){
        SysMenu sysMenu = sysMenuService.queryByPK(id);
        return R.success().put("sysMenu",sysMenu);
    }

    @ResponseBody
    @RequestMapping("save.do")
    public R save(SysMenu sysMenu){
        if(sysMenuService.add(sysMenu)>0){
            return R.success("添加保存信息成功");
        }
        return R.error("添加保存信息失败");
    }

    @ResponseBody
    @RequestMapping("edit.do")
    public R edit(Long id){
        SysMenu sysMenu = sysMenuService.queryByPK(id);
        return R.success().put("sysMenu",sysMenu);
    }

    @ResponseBody
    @RequestMapping("update.do")
    public R update(SysMenu sysMenu){
        if(sysMenuService.modifyByPk(sysMenu) > 0){
            return R.success("修改更新数据成功");
        }
        return R.error("修改更新数据失败");
    }

    @ResponseBody
    @RequestMapping("del.do")
    public R del(Long id){
        if(sysMenuService.logicDelByPk(id) > 0){
            return R.success("删除数据成功");
        }
        return R.error("删除数据失败");
    }

    /**
     * created by zhaijianchao on 2018.08.06
     * 获取当前登陆用户所拥有的菜单权限
     * */
    @MLogin
    @ResponseBody
    @RequestMapping("userMenu.do")
    public R userMenu(){
        List<SysMenuVo> roots = sysMenuFacade.getUserMenu();
        return R.success("获取成功").put("data",roots);
    }

    /**
     * 获取列表内按钮权限
     * created by zhaijianchao on 2018-08-10
     * */
    @MLogin
    @ResponseBody
    @RequestMapping("userButtonList.do")
    public R userButtonList(Long id){
        List<SysMenuVo> menuVos = sysMenuFacade.getUserListMenu(id,MenuTypeEnum.BUTTON_LIST.getCode(),PlatformEnum.MANAGE.getCode());
        return R.success(menuVos);
    }

}
