package com.xiang.cloud.interceptor.m;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiang.cloud.common.constants.SysConstant;
import com.xiang.cloud.common.enums.user.UserTypeEnum;
import com.xiang.cloud.common.util.http.SendMsgUtil;
import com.xiang.base.core.JwtHelper;
import com.xiang.base.core.cache.RedisHelper;
import com.xiang.base.core.user.UserHelper;
import com.xiang.base.model.AuthMenu;
import com.xiang.base.model.AuthUser;
import com.xiang.base.response.R;
import com.xiang.cloud.annotation.login.MLogin;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * sgcc管理系统登陆认证拦截
 * @author zhaijianchao
 * @date 2018-08-07
 */
public class SgccLoginInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(SgccLoginInterceptor.class);
    @Autowired
    private RedisHelper redisHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        MLogin methodAnnotation = method.getAnnotation(MLogin.class);
        // 有 @MLogin 注解，需要认证
        if (methodAnnotation != null) {
            System.out.println(request.getHeader("access_token"));
            String accessToken = request.getParameter("access_token");
            if(StringUtils.isBlank(accessToken)){
                R r = R.logout("未获取登陆令牌");
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            DecodedJWT decodedJWT = null;
            try {
                //验证解析jwt签名
                decodedJWT = JwtHelper.verifyJwt(accessToken);
            } catch (UnsupportedEncodingException e) {
                logger.info(e.getMessage());
                R r = R.logout(e.getMessage());
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            } catch (Exception e) {
                logger.info(e.getMessage());
                R r = R.logout(e.getMessage());
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            AuthUser authUser = UserHelper.getAuthUser();
            JSONObject jsonObject = (JSONObject) redisHelper.get(authUser.getUid());
            if (!authUser.getUserType().equals(UserTypeEnum.MANAGE.getCode())) {
                R r = R.logout("非后台管理用户");
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            if (jsonObject == null) {
                R r = R.logout("重新登陆");
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            if(authUser == null){
                R r = R.logout("用户未登陆");
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            if(!authUser.getLoginName().equals(decodedJWT.getClaim(AuthUser.LOGIN_NAME).asString())){
                R r = R.logout("登陆令牌无效");
                SendMsgUtil.sendJsonMessage(response,r);
                return false;
            }
            //从redis获取当前用户所有操作权限判断是否为空
            JSONArray menus = (JSONArray) redisHelper.get(SysConstant.LoginConstant.AUTH_USER_MENU_LIST_+authUser.getLoginName());
            if (menus != null && !menus.isEmpty()) {
                List<AuthMenu> menuList = JSONObject.parseArray(menus.toJSONString(), AuthMenu.class);
                //如果登陆验证注解中value值不为空，根据当前用户所拥有的所有权限列表循环判断是否拥有当前权限
                if (StringUtils.isNotBlank(methodAnnotation.value())) {
                    for (AuthMenu authMenu : menuList) {
                        if (authMenu.getUrl().equals(methodAnnotation.value())) {
                            return true;
                        }
                    }
                    R r = R.logout("无此操作权限");
                    SendMsgUtil.sendJsonMessage(response, r);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
