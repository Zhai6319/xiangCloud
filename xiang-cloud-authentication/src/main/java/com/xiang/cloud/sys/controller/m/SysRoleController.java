package com.xiang.cloud.sys.controller.m;

import com.xiang.base.controller.BaseController;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import com.xiang.cloud.sys.model.SysRole;
import com.xiang.cloud.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统后台用户Controller
 * created by zhaijianc on 2018-08-06
 * @author zhaijianchao
 * */
@RequestMapping("m/sysRole")
@Controller
public class SysRoleController extends BaseController{
    @Autowired
    private SysRoleService sysRoleService;

    @ResponseBody
    @RequestMapping("page.do")
    public R page(SysRole sysRole){
        Params params = Params.create();
        params.add(sysRole);
        Page page = this.getPage();
        PagedResult<SysRole> pages = sysRoleService.page(params,page);
        return R.success().put("pageData",pages);
    }

    @ResponseBody
    @RequestMapping("detail.do")
        public R detail(Long id){
        SysRole sysRole = sysRoleService.queryByPK(id);
        return R.success().put("sysRole",sysRole);
    }

    @ResponseBody
    @RequestMapping("save.do")
    public R save(SysRole sysRole){
        if(sysRoleService.add(sysRole)>0){
            return R.success("添加保存信息成功");
        }
        return R.error("添加保存信息失败");
    }

    @ResponseBody
    @RequestMapping("edit.do")
    public R edit(Long id){
        SysRole sysRole = sysRoleService.queryByPK(id);
        return R.success().put("sysRole",sysRole);
    }

    @ResponseBody
    @RequestMapping("update.do")
    public R update(SysRole sysRole){
        if(sysRoleService.modifyByPk(sysRole) > 0){
            return R.success("修改更新数据成功");
        }
        return R.error("修改更新数据失败");
    }

    @ResponseBody
    @RequestMapping("del.do")
    public R del(Long id){
        if(sysRoleService.logicDelByPk(id) > 0){
            return R.success("删除数据成功");
        }
        return R.error("删除数据失败");
    }

}
