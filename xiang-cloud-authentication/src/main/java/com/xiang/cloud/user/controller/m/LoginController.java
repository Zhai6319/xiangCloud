package com.xiang.cloud.user.controller.m;

import com.xiang.cloud.annotation.login.MLogin;
import com.xiang.cloud.common.constants.SysConstant;
import com.xiang.cloud.common.util.VerifyCodeUtil;
import com.xiang.base.controller.BaseController;
import com.xiang.base.core.AccessToken;
import com.xiang.base.core.http.HttpHolder;
import com.xiang.base.core.http.SessionHelper;
import com.xiang.base.response.R;
import com.xiang.cloud.user.facade.SysUserFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author zhaijianchao
 * Created by zhaijianc on 2018/06/22
 */
@CrossOrigin(value = "*",maxAge = 3600)
@Controller
@RequestMapping("m")
public class LoginController extends BaseController {

    @Autowired
    private SysUserFacade sysUserFacade;
    /**
     * 用户登陆
     * created by zhaijianchao on 2018-08-06
     * @param loginName 登陆名
     * @param password 密码
     * @param verifyCode 验证码
     * */
    @ResponseBody
    @RequestMapping(value = "login",method = {RequestMethod.POST,RequestMethod.GET})
    public R login(String loginName,String password,String verifyCode) throws UnsupportedEncodingException {
        String checkCode = (String) SessionHelper.getSessionValue(SysConstant.LoginConstant.VERIFY_CODE);
        if (StringUtils.isBlank(verifyCode)) {
            return R.error("请填写验证码");
        }
        if (!verifyCode.equalsIgnoreCase(checkCode)) {
            return R.error("验证码不正确");
        }
        AccessToken accountToken = sysUserFacade.doMLogin(loginName,password);
        return R.success(accountToken);
    }

    /**
     * 获取登陆验证码图片流
     * */
    @RequestMapping(value = "verifyCode.jpg")
    public void verifyCode(){
        try {
            HttpServletResponse response = HttpHolder.getHttpResponse();
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            int w = 100, h = 30;
            VerifyCodeUtil.outputVerifyImage(w,h,response.getOutputStream(),4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 清楚缓存
     * created by zhaijianchao on 2018/08/15
     * */
    @MLogin
    @ResponseBody
    @RequestMapping(value = "clearCache.do")
    public R clearCache(){
        SessionHelper.initSession();
        return R.success();
    }

}
