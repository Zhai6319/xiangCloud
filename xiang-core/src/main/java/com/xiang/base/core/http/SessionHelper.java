package com.xiang.base.core.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * session操作类
 * @author zhaijianchao
 */
public class SessionHelper {

    /**
     * 获取Session
     * */
    public static HttpSession getSession(){
        System.out.println(HttpHolder.getHttpRequest().getSession().getId());
        return HttpHolder.getHttpRequest().getSession();
    }

    /**
     * 存放 session
     * */
    public static void putSession(String sessionKey, Object sessionValue){
        SessionHelper.getSession().setAttribute(sessionKey,sessionValue);
    }

    /**
     * 获取 session
     * */
    public static Object getSessionValue(String sessionKey){
        return SessionHelper.getSession().getAttribute(sessionKey);
    }

    /**
     * 初始化session信息
     * */
    public static void initSession(){
        HttpServletRequest request = HttpHolder.getHttpRequest();
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
    }
}
