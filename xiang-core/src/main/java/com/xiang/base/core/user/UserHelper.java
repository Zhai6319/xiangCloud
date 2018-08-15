package com.xiang.base.core.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiang.base.core.JwtHelper;
import com.xiang.base.core.http.HttpHolder;
import com.xiang.base.model.AuthUser;
import com.xiang.cloud.common.exception.LoginException;

import java.io.UnsupportedEncodingException;

/**
 * @author zhaijianchao
 */
public class UserHelper {

    /**
     * 获取当前登陆用户信息
     * */
    public static AuthUser getAuthUser() {
        try {
            String accessToken = HttpHolder.getHttpRequest().getParameter("access_token");
            DecodedJWT decodedJWT = JwtHelper.verifyJwt(accessToken);
            AuthUser authUser = new AuthUser();
            authUser.setUid(decodedJWT.getClaim("uid").asString());
            authUser.setUserType(decodedJWT.getClaim("userType").asString());
            authUser.setCorpId(decodedJWT.getClaim("corpId").asLong());
            authUser.setEmail(decodedJWT.getClaim("email").asString());
            authUser.setId(decodedJWT.getClaim("id").asLong());
            authUser.setIdcard(decodedJWT.getClaim("idcard").asString());
            authUser.setLoginName(decodedJWT.getClaim("loginName").asString());
            authUser.setMobile(decodedJWT.getClaim("mobile").asString());
            authUser.setName(decodedJWT.getClaim("name").asString());
            authUser.setNickName(decodedJWT.getClaim("nickName").asString());
            return authUser;
        } catch (UnsupportedEncodingException e) {
            throw new LoginException(e.getMessage());
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }

    }

}
