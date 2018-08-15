package com.xiang.cloud.zuul.controller.m;

import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import com.xiang.cloud.zuul.model.SysUser;
import com.xiang.cloud.zuul.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("m/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("list")
    public R list(SysUser sysUser){
        Params params = Params.create();
        params.add(sysUser);
        List<SysUser> sysUserList = sysUserService.queryByParams(params);
        return R.success().put("sysUserList",sysUserList);
    }
}
