package com.xiang.base.core;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiang.base.model.AuthUser;
import com.xiang.cloud.common.exception.LoginException;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaijianchao
 */
public class JwtHelper {

    /**
     * 创建token
     * */
    public static String createJWT(AuthUser authUser) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("loginName",authUser.getLoginName())
                .withClaim("id",authUser.getId())
                .withClaim("uid",authUser.getUid())
                .withClaim("idcard",authUser.getIdcard())
                .withClaim("userType",authUser.getUserType())
                .withClaim("nikeName",authUser.getNickName())
                .withClaim("name",authUser.getName())
                .withClaim("mobile",authUser.getMobile())
                .withClaim("email",authUser.getEmail())
                .withClaim("corpId",authUser.getCorpId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1*24*60*60*1000))
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     * */
    public static DecodedJWT verifyJwt(String accessToken) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(accessToken)) {
            throw new LoginException("缺少签名验证信息");
        }
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(accessToken);
        return decodedJWT;
    }




    public static void main(String[] args) throws UnsupportedEncodingException {
        AuthUser authUser = new AuthUser();
        authUser.setId(323l);
        authUser.setLoginName("sdfsdf");
        authUser.setCorpId(2323l);
        authUser.setEmail("sdfsdf");
        authUser.setMobile("dsfsdf");
        String jwt = JwtHelper.createJWT(authUser);
        DecodedJWT decodedJWT =JwtHelper.verifyJwt(jwt);
        System.out.println(decodedJWT.getClaim("loginName").asString());
    }




}
