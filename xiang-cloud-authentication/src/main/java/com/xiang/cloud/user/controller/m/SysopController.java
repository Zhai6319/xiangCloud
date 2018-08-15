package com.xiang.cloud.user.controller.m;

import com.xiang.cloud.common.enums.user.LoginModeEnum;
import com.xiang.cloud.common.enums.user.UserTypeEnum;
import com.xiang.base.controller.BaseController;
import com.xiang.cloud.factory.IGenerator;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import com.xiang.cloud.annotation.login.MLogin;
import com.xiang.cloud.user.controller.m.request.SysopQueryRequest;
import com.xiang.cloud.user.controller.m.request.SysopSaveRequest;
import com.xiang.cloud.user.controller.m.request.SysopUpdateRequest;
import com.xiang.cloud.user.controller.m.response.SysopQueryResponse;
import com.xiang.cloud.user.dto.SysUserDTO;
import com.xiang.cloud.user.facade.SysUserFacade;
import com.xiang.cloud.user.model.SysUser;
import com.xiang.cloud.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 系统操作员用户
 * created by zhaijianc on 2018.5.01
 * @author zhaijianchao
 */
@Controller
@RequestMapping("m/user/sysop")
public class SysopController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private IGenerator iGenerator;
    @Autowired
    private SysUserFacade sysUserFacade;

    /**
     * 后台操作员用户分页列表
     * created by zhaijianchao on 2018.05.01
     * @param sysopQueryRequest 管理系统操作员前端请求参数接收
     * @return
     * */
    @MLogin("sys/user/sysop/page")
    @ResponseBody
    @RequestMapping(value = "page.do",method = {RequestMethod.POST,RequestMethod.GET})
    public R page(SysopQueryRequest sysopQueryRequest){
        //数据转换：请求对象转换为查询对象
        SysUser sysUser = iGenerator.convert(sysopQueryRequest,SysUser.class);
        sysUser.setUserType(UserTypeEnum.MANAGE.getCode());
        Params params = Params.create();
        params.add(sysUser);
        Page page = this.getPage();
        PagedResult<SysUser> pages = sysUserService.page(params,page);
        //数据转换：数据对象转换为响应对象
        PagedResult<SysopQueryResponse> pagedResult = iGenerator.convert(pages,SysopQueryResponse.class);
        return R.page(pagedResult);
    }

    /**
     * 用户明细
     * created by zhaijianchao on 2018/05/01
     * @param id 查询用户ID
     * @return 返回查询结果
     * */
    @MLogin("sys/user/sysop/page/detail")
    @ResponseBody
    @RequestMapping(value = "detail.do",method = {RequestMethod.POST,RequestMethod.GET})
    public R detail(Long id){
        SysUser sysUser = sysUserService.queryByPK(id);
        SysopQueryResponse sysopQueryResponse = iGenerator.convert(sysUser,SysopQueryResponse.class);
        return R.success().put("sysUser",sysopQueryResponse);
    }

    /**
     * 添加系统操作员用户
     * @param sysopSaveRequest 基础请求参数
     * @return 返回处理结果
     * */
    @MLogin("sys/user/sysop/page/save")
    @ResponseBody
    @RequestMapping(value = "save.do",method = {RequestMethod.POST,RequestMethod.GET})
    public R save(@Valid SysopSaveRequest sysopSaveRequest){
        SysUserDTO sysUserDTO = iGenerator.convert(sysopSaveRequest, SysUserDTO.class);
        sysUserDTO.setLoginName(sysopSaveRequest.getUserName());
        sysUserDTO.setLoginToken(sysopSaveRequest.getPassword());
        sysUserDTO.setLoginMode(LoginModeEnum.LOGIN_NAME.getCode());
        sysUserFacade.doCreateSysop(sysUserDTO);
        return R.success("创建系统操作员成功");
    }


    @MLogin("sys/user/sysop/page/update")
    @ResponseBody
    @RequestMapping(value = "update.do",method = {RequestMethod.POST,RequestMethod.GET})
    public R update(@Valid SysopUpdateRequest sysopUpdateRequest){
        SysUser sysUser = iGenerator.convert(sysopUpdateRequest,SysUser.class);
        if(sysUserService.modifyByPk(sysUser) > 0){
            return R.success("修改更新数据成功");
        }
        return R.error("修改更新数据失败");
    }

    @MLogin("sys/user/sysop/page/del")
    @ResponseBody
    @RequestMapping(value = "del.do",method = {RequestMethod.POST,RequestMethod.GET})
    public R del(Long id){
        if(sysUserService.logicDelByPk(id) > 0){
            return R.success("删除数据成功");
        }
        return R.error("删除数据失败");
    }
}
