package com.xiang.cloud.user.facade.impl;

import com.alibaba.fastjson.JSON;
import com.xiang.cloud.common.constants.SysConstant;
import com.xiang.cloud.common.enums.sys.PlatformEnum;
import com.xiang.cloud.common.enums.user.UserStatusEnum;
import com.xiang.cloud.common.enums.user.UserTypeEnum;
import com.xiang.cloud.common.exception.BusinessException;
import com.xiang.cloud.common.util.TxtUtil;
import com.xiang.base.core.AccessToken;
import com.xiang.base.core.JwtHelper;
import com.xiang.base.core.cache.RedisHelper;
import com.xiang.base.core.http.HttpHolder;
import com.xiang.base.facade.impl.BaseFacadeImpl;
import com.xiang.base.model.AuthMenu;
import com.xiang.base.model.AuthUser;
import com.xiang.cloud.common.exception.LoginException;
import com.xiang.cloud.factory.IGenerator;
import com.xiang.cloud.sys.dto.SysMenuDTO;
import com.xiang.cloud.sys.service.SysMenuService;
import com.xiang.cloud.user.dto.SysUserDTO;
import com.xiang.cloud.user.facade.SysUserFacade;
import com.xiang.cloud.user.model.SysLoginLog;
import com.xiang.cloud.user.model.SysUser;
import com.xiang.cloud.user.model.SysUserLogin;
import com.xiang.cloud.user.service.SysLoginLogService;
import com.xiang.cloud.user.service.SysUserLoginService;
import com.xiang.cloud.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author zhaijianchao
 */
@Service
public class SysUserFacadeImpl extends BaseFacadeImpl implements SysUserFacade{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserLoginService sysUserLoginService;
    @Autowired
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private IGenerator iGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessToken doMLogin(String loginName,String password) throws UnsupportedEncodingException {
        SysUserLogin sysUserLogin = sysUserLoginService.getByLoginName(loginName);
        SysUser sysUser = sysUserService.queryByPK(sysUserLogin.getUserId());
        if (StringUtils.isBlank(loginName)) {
            throw new LoginException("用户名不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            throw new LoginException("请输入密码！");
        }
        if (sysUser == null) {
            throw new LoginException("用户不存在！");
        }
        String md5Password = TxtUtil.digest(password);
        if (md5Password.compareTo(sysUserLogin.getLoginToken()) != 0 ) {
            throw new LoginException("密码错误！");
        }
        if (!UserTypeEnum.MANAGE.getCode().equals(sysUser.getUserType())) {
            throw new LoginException("非SGCC后台管理用户！");
        }
        if (UserStatusEnum.LOCK.getCode().equals(sysUser.getUserStatus())) {
            throw new LoginException("被锁定的用户");
        }
        if (UserStatusEnum.LOGOUT.getCode().equals(sysUser.getUserStatus())) {
            throw new LoginException("用户已注销");
        }

        //拼装加密参数
        AuthUser authUser = new AuthUser();
        authUser.setUid(UUID.randomUUID().toString());
        authUser.setId(sysUser.getId());
        authUser.setLoginName(sysUserLogin.getLoginName());
        authUser.setCorpId(sysUser.getCorpId());
        authUser.setEmail(sysUser.getEmail());
        authUser.setMobile(sysUser.getMobile());
        authUser.setName(sysUser.getName());
        authUser.setIdcard(sysUser.getIdCard());
        authUser.setNickName(sysUser.getNickName());
        authUser.setUserType(sysUser.getUserType());
        //获取登陆令牌
        String jwt = JwtHelper.createJWT(authUser);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(jwt);
        //修改用户登陆信息
        sysUser.setLoginCount(sysUser.getLoginCount()+1);
        sysUserService.modifyByPk(sysUser);
        //插入登陆日志
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setLoginAddress(HttpHolder.getAdd());
        sysLoginLog.setLoginName(sysUserLogin.getLoginName());
        sysLoginLog.setUserId(sysUser.getId());
        sysLoginLog.setLoginTime(new Date());
        sysLoginLog.setCorpId(sysUser.getCorpId());
        sysLoginLog.setLoginIp(HttpHolder.getClientIp());
        sysLoginLog.setLoginPlatform(PlatformEnum.MANAGE.getCode());
        sysLoginLogService.add(sysLoginLog);
        //将登陆令牌中uid和用户绑定存入其中
        redisHelper.set(authUser.getUid(),authUser,1*24*60*60*1000);
        System.out.println(JSON.toJSONString(redisHelper.get(authUser.getUid())));
        //获取用户所有权限拷贝到授权菜单集合存入redis缓存
        List<SysMenuDTO> sysMenuList = sysMenuService.queryByUserIdAndMenuType(null,sysUser.getId(),null,PlatformEnum.MANAGE.getCode());
        List<AuthMenu> authUserList = iGenerator.convert(sysMenuList,AuthMenu.class);
        redisHelper.set(SysConstant.LoginConstant.AUTH_USER_MENU_LIST_+authUser.getLoginName(),authUserList);
        return accessToken;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateSysop(SysUserDTO sysUserDTO) {
        SysUser sysUser = iGenerator.convert(sysUserDTO,SysUser.class);
        sysUser.setUserStatus(UserStatusEnum.NORMAL.getCode());
        sysUser.setUserType(UserTypeEnum.MANAGE.getCode());
        if (sysUserService.add(sysUser) <= 0) {
            throw new BusinessException("创建系统操作员失败");
        }
        SysUserLogin sysUserLogin = iGenerator.convert(sysUserDTO,SysUserLogin.class);
        sysUserLogin.setUserId(sysUser.getId());
        sysUserLogin.setLoginToken(TxtUtil.digest(sysUserDTO.getLoginToken()));
        sysUserLogin.setLoginMode(sysUserDTO.getLoginMode());
        if (sysUserLoginService.add(sysUserLogin) <= 0) {
            throw new BusinessException("创建用户登陆信息失败");
        }
    }

}
